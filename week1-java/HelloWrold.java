/*
 * Thread basic
 * Using synchronized lock
 */

package Day1;

public class HelloWrold {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			public void run() {
				System.out.println("Hello world from thread");
			}
		};
		t1.start();
		Thread.yield();
		System.out.println("Hello world from main thread");
		t1.join();
	}
}

class HelloWorld implements Runnable {
	public void run() {
		System.out.println("Hello world");
	}
}
