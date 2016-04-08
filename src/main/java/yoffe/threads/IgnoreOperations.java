package yoffe.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class IgnoreOperations {

	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(6);
		final AtomicInteger total = new AtomicInteger(0);
		final AtomicBoolean allowed = new AtomicBoolean(true);
		
		/*	for(int i =0; i < 1000; i++){
		Runnable runnable = new Runnable(){
			public void run(){
				total.incrementAndGet();
			}
		};
		service.execute(runnable);
	}*/
		
		ActionListener listener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		};
			

			
		
	
		
			for(int i =0; i < 1000; i++){
		Runnable runnable = new Runnable(){
			public void run(){
				total.incrementAndGet();
			}
		};
		service.execute(runnable);
	}
		service.shutdown();
		service.awaitTermination(12,  TimeUnit.DAYS);
		System.out.println(total);
	}
}