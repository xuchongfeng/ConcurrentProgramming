package Day1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable{
	private final int THINKING = 0;
	private final int EATING = 1;
	private Philosopher left;
	private Philosopher right;
	private int status;
	public int getStatus() {
		return this.status;
	}
	public Philosopher(Philosopher left, Philosopher right){
		this.left = left;
		this.right = right;
		status = THINKING;
	}
	public void eat() throws InterruptedException {
		/*if(left < right) {
			int tmp = left;
			left = right;
			right = tmp;
		}*/
		Table.lock.lock();
		try {
			while(left.status == EATING || right.status == EATING)
				Table.condition.await();
			status = EATING;
		} finally {
			Table.lock.unlock();
		}
	}
	public void think() throws InterruptedException {
		Table.lock.lock();
		try {
			status = THINKING;
			Table.condition.signal();
		} finally {
			Table.lock.unlock();
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
	public static ReentrantLock lock;
	public static Condition condition;
}

public class Lock {
	public static void main(String[] args) throws InterruptedException {
		Table.chopstick = new Chopstick[5];
		Table.lock = new ReentrantLock();
		Table.condition = Table.lock.newCondition();
		Philosopher[] p =new Philosopher[5];
		Thread[] t = new Thread[5];
		for(int i=0;i<5;i++) Table.chopstick[i] = new Chopstick();
		/*for(int i=0;i<5;i++){
			p[i] = new Philosopher(i, (i+1)%5);
			t[i] = new Thread(p[i]);
		}*/
		for(int i=0;i<5;i++) {
			t[i].start();
			System.out.println("Thread" + i + "Start");
			//t[i].join();
		}
		for(int i=0;i<5;i++) t[i].join();
	}
}
