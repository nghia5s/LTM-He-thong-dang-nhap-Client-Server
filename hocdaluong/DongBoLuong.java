package hocdaluong;

import java.util.Random;

public class DongBoLuong {

	public static void main(String[] args) {
		// Yeu cau xay dung chuong trinh tinh tong 100 lan 2 so ngau nhien x,y
		//- tao luong sinh so ngau nhieen x 100 lan
		//- tao luong sinh so ngau nhien y 100 lan
		//- luong tinh tong 2 so ngau nhien x,y 100 lan
		// Quy uoc : khoa - flag
		//flag =1 : luong sinh so ngau nhien x duoc thucj thi
		// flag =2 : luong sinh co ngau nhien y dc thuc thi
		// flag =3 : luong tinh tong duoc thucj thi
		
		Data data = new Data();
		data.setFlag(1);
		//luong tao so ngau nhien x
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =0 ; i<100; i++) {
					synchronized (data) {
					try {
						while (data.getFlag()!=1 ) {
							
								data.wait();
						} 
						int x = new Random().nextInt(50);
						data.setX(x);
						System.out.println("X= "+ x);
						data.setFlag(2);
						data.notifyAll();
					}catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
					
					}
				
			
		}).start();
		
		new Thread (new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i =0 ; i<100; i++) {
					synchronized (data) {
					try {
						while (data.getFlag()!=2 ) {
							
								data.wait();
						} 
						int y = new Random().nextInt(30);
						data.setX(y);
						System.out.println("y= "+ y);
						data.setFlag(3);
						data.notifyAll();
					}catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
					
					}

			
		}).start();
		// Luong tinh tong 

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i =0 ; i<100; i++) {
					synchronized (data) {
					try {
						while (data.getFlag()!=3 ) {
								data.wait();
						} 
						System.out.println("x + y= " + data.tinhTong());
						data.setFlag(1);
						data.notifyAll();
					}catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
					
					}
			
		}).start();
	}

}
