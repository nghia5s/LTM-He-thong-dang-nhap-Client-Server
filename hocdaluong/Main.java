package hocdaluong;

public class Main {

	public static void main(String[] args) {
		// Xay dung chuong trinh chay song song cac luong
		//1 luong in ra so 1
		//2 luong in ra so 2
		// tao luong co 2 cach
		//1 - ke thua tu Thread
		// Tao luong
		Luong1 luong1 = new Luong1();//state new
		luong1.start();// state runable
		//2 - Trien khai tu interface Runnable
		// Tao luong
		Luong2 luong2 = new Luong2();
		Thread thread = new Thread(luong2);// stare new
		thread.start();//State runnable
		//3 - Viet lai cach 2 ngan gon
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i =0; i<1000;i++) {
					System.out.print(3);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
	}

}
