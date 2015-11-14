package Day1;


class Philosopher implements Runnable{
	private int left;
	private int right;
	public Philosopher(int left, int right){
		this.left = left;
		this.right = right;
	}
	public void eat() throws InterruptedException {
		/*if(left < right) {
			int tmp = left;
			left = right;
			right = tmp;
		}*/
		synchronized(Table.chopstick[left]) {
			synchronized(Table.chopstick[right]) {
				System.out.println(left + right + "eating");
			}
		}
	}
	public void run() {
		while(true)
			try {
				Thread.yield();
				eat();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}

class Chopstick {
}

class Table {
	public static Chopstick[] chopstick;
}

public class Dinner {
	public static void main(String[] args) throws InterruptedException {
		Table.chopstick = new Chopstick[5];
		Philosopher[] p =new Philosopher[5];
		Thread[] t = new Thread[5];
		for(int i=0;i<5;i++) Table.chopstick[i] = new Chopstick();
		for(int i=0;i<5;i++){
			p[i] = new Philosopher(i, (i+1)%5);
			t[i] = new Thread(p[i]);
		}
		for(int i=0;i<5;i++) {
			t[i].start();
			//System.out.println("Thread" + i + "Start");
			//t[i].join();
		}
		for(int i=0;i<5;i++) t[i].join();
	}
}
