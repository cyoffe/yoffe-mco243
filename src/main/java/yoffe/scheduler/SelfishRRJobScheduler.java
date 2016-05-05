package yoffe.scheduler;

import java.util.ArrayList;
import java.util.Collections;

public class SelfishRRJobScheduler extends RoundRobinJobScheduler {

	private ArrayList<Job> holdingQueue;

	public SelfishRRJobScheduler(ArrayList<Job> jobs) {
		super(new ArrayList<Job>());
		holdingQueue = jobs;
		placeInActiveJobQueue();
	}

	@Override
	public void run() {
		Job lastJob = null;

		if (!jobs.isEmpty() || !holdingQueue.isEmpty()) {
			placeInActiveJobQueue();
			updateJobPriorities();

			if (!jobs.isEmpty()) {
				Collections.sort(jobs, comparator);
				Job job = jobs.get(0);

				int actualTimeSlice = executeJob(job);

				totalTime += actualTimeSlice;

				if (job != lastJob) {
					totalTime += OVERHEAD;
					lastJob = job;
				}
			}
		}
	}

	private void updateJobPriorities() {
		int nextPriority;
		for (Job job : holdingQueue) {
			nextPriority = job.getPriority().ordinal() + 1;
			job.setPriority(Priority.values()[nextPriority]);
		}

	}

	private void placeInActiveJobQueue() {
//		for (Job job : holdingQueue) {
//			if (job.getPriority().equals(Priority.REAL_TIME)) {
//				holdingQueue.remove(job);
//				jobs.add(job);
//			}
//		}
		
		for (int i = 0; i < holdingQueue.size(); i++) {
			Job job = holdingQueue.get(i);
			if (job.getPriority().equals(Priority.REAL_TIME)) {
				holdingQueue.remove(job);
				jobs.add(job);
			}
		}

	}

}
