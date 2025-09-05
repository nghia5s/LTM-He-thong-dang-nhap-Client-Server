package hocdaluong;

public class Luong2 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i =0; i<1000;i++) {
			System.out.print(2);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
