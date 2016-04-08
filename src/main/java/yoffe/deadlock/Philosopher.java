package yoffe.deadlock;

import java.util.logging.Logger;

public class Philosopher extends Thread {

	private static final Logger LOGGER = Logger.getLogger(Philosopher.class.getSimpleName());
	
	private Fork f1;
	private Fork f2;
	private String name;
	private Waiter waiter;


	public Philosopher(String name, Waiter waiter, Fork f1, Fork f2) {
		this.name = name;
		this.f1 = f1;
		this.f2 = f2;
		this.waiter = waiter;
	}

	public void run() {
		while (true) {
			think();
			eat();
		}
	}
	
	public void eat() {
		LOGGER.info(this.toString() + " attempting to eat");
		if(waiter.tryToEat(f1, f2)){
			LOGGER.info(this.toString() + "eating");
			waitForAFewSeconds(10);
			f1.setInUse(false);
			f2.setInUse(false);
			
		}
		LOGGER.info(this.toString() + " done eating");
//		System.out.println(this + " trying to pick up " + f1);
//		synchronized(f1) {
//			waitForAFewSeconds(10);
//			System.out.println(this + " trying to pick up " + f2);
//			synchronized(f2) {
//				System.out.println(this + " eating...");
//				waitForAFewSeconds(10);
//			}
//			System.out.println(this + " put down " + f1);
//		}
//		System.out.println(this + " put down " + f2);
	}
	
	public void think() {
		waitForAFewSeconds(5);
	}

	private void waitForAFewSeconds(int seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + "]";
	}
	
	
}
