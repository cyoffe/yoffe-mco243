package yoffe.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class JobScheduler implements Runnable {

	// max amount of time each job runs for
	protected final int TIME_SLICE = 10;

	// amount of time it takes to switch jobs
	protected final int OVERHEAD = 3;

	protected ArrayList<Job> jobs;
	protected Comparator<Job> comparator;
	protected static int totalTime;
	protected static int numJobsCompleted;
	private JobType jobType;
	private static Random random = new Random();

	public JobScheduler(ArrayList<Job> jobs, Comparator<Job> comparator) {
		this.comparator = comparator;
		this.jobs = jobs;
		numJobsCompleted = 0;
		totalTime = 0;
	}

	@Override
	public void run() {

		Job lastJob = null;

		while (!jobs.isEmpty()) {
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

	public int executeJob(Job job) {
		job.setState(JobState.RUNNING);
		job.setLastRanAtTime(System.currentTimeMillis());
		int timeLeftToRun = job.getTimeLeftToRun();
		int actualTimeSlice = computeActualTimeSlice(job, timeLeftToRun);
		timeLeftToRun -= actualTimeSlice;

		job.setTimeLeftToRun(timeLeftToRun);

		if (timeLeftToRun == 0) {
			jobs.remove(0);
			numJobsCompleted++;
		} else {
			job.setState(JobState.READY);
		}
		return actualTimeSlice;
	}

	protected int computeActualTimeSlice(Job job, int timeLeftToRun) {
		int actualTimeSlice;
		if (job.getType() == JobType.IO) {
			actualTimeSlice = Math.min(random.nextInt(TIME_SLICE),
					timeLeftToRun);
		} else {
			actualTimeSlice = Math.min(TIME_SLICE, timeLeftToRun);
		}
		return actualTimeSlice;
	}

	public int getNumJobsCompleted() {
		return numJobsCompleted;
	}

	public int getTotalTime() {
		return totalTime;
	}

}
