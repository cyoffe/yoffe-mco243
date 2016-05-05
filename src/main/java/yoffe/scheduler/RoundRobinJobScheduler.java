package yoffe.scheduler;

import java.util.ArrayList;

public class RoundRobinJobScheduler extends JobScheduler {

	private int timeSliceLeft = TIME_SLICE;

	public RoundRobinJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new FifoJobComparator());
	}

	@Override
	public int executeJob(Job job){
		job.setState(JobState.RUNNING);
		job.setLastRanAtTime(System.currentTimeMillis());
		int timeLeftToRun = job.getTimeLeftToRun();
		int actualTimeSlice = computeActualTimeSlice(job, timeLeftToRun);
		timeLeftToRun -= actualTimeSlice;

		job.setTimeLeftToRun(timeLeftToRun);

		if (timeLeftToRun == 0) {
			jobs.remove(0);
			numJobsCompleted++;
			timeSliceLeft = TIME_SLICE;
		} else if (timeSliceLeft <= 0){
			jobs.remove(0);
			jobs.add(job);
		} 
		else {
			job.setState(JobState.READY);
		}
		return actualTimeSlice;
		
		
	}

}
