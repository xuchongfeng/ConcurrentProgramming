package Day1;

public class Count {
	public static int count = 0;
	public static void main(String[] args) throws InterruptedException {
		Adder a1 = new Adder();
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a1);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count);
	}

}

class Adder implements Runnable{
	public synchronized void run() {
		for(int i = 0; i < 10000; i++) {
			Count.count++;
		}
	}
}
