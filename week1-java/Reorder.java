package Day1;

public class Reorder{
	public static void main(String[] args) throws InterruptedException{
		for(int i=0;;i++) {
			System.out.print(i + ",");
			Inner.run();
		}
	}
}


class Inner {
	private static int a = 0, b = 0;
	private static int x = 0, y = 0;
	
	public static void run() throws InterruptedException {
		a = b = x = y = 0;
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				// wait for t2
				for(int i=0;i<100000;i++){}
				a = 1;
				x = b;
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				b = 1;
				y = a;
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(x + ":" + y);
		
		if(x == 0 && y == 0){
			Thread.sleep(100000);
		}
		
	}
}
