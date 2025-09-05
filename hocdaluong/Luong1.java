package hocdaluong;

public class Luong1 extends Thread {
	@Override
	public void run() {
		// Dinh nghia luong hoat dong
		for (int i =0; i<1000;i++) {
			System.out.print(1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
