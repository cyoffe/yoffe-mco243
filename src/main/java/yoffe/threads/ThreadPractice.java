package yoffe.threads;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPractice {
		 int totalDownloaded = 0;
		 int totalProcessed = 0;
		
		public void downloadFile(){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(this){
			totalDownloaded++;
			}
		}
		public void processFile(){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized(this){
				totalProcessed++;
			}
		}
		public ThreadPractice() throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(6);
			
			for (int i = 0; i < 10000; i++){
				Runnable runnable = new Runnable(){
					public void run(){
						downloadFile();
						processFile();
					}
				};
				service.execute(runnable);
			}
			service.shutdown();
			service.awaitTermination(12,  TimeUnit.DAYS);
			System.out.println(totalDownloaded + ", " + totalProcessed);
		

		}

		public static void main(String[] args) throws InterruptedException {
			ThreadPractice down = new ThreadPractice();
		
		}

	}