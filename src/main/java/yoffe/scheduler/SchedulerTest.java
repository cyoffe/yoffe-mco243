package yoffe.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchedulerTest {

	public static void main(String[] args) {
		List<Job> jobs = Arrays.asList(
				new Job(Priority.HIGH,JobType.COMPUTATION, "1", 100, 100, 45L), 
				new Job(Priority.REAL_TIME, JobType.IO, "2", 200, 50, 23L), 
				new Job(Priority.HIGH,JobType.COMPUTATION, "3", 100, 75, 65L),
				new Job(Priority.MEDIUM, JobType.IO, "4", 1000, 87, 31L), 
				new Job(Priority.REAL_TIME, JobType.COMPUTATION, "5", 1350, 90, 46L), 
				new Job(Priority.MEDIUM, JobType.COMPUTATION, "6", 100, 150, 100L), 
				new Job(Priority.LOW, JobType.COMPUTATION, "7", 500, 100, 65L), 
				new Job(Priority.HIGH,JobType.IO, "8", 100, 78, 59L), 
				new Job(Priority.REAL_TIME,JobType.COMPUTATION, "9", 100, 67, 90L), 
				new Job(Priority.HIGH,JobType.COMPUTATION, "10", 100, 20, 1L), 
				new Job(Priority.LOW,JobType.IO, "11", 100, 6, 80L), 
				new Job(Priority.HIGH, JobType.COMPUTATION, "12", 100, 10, 72L)
  
		);

		JobScheduler priority = new PriorityJobScheduler(new ArrayList<Job>(jobs));
		priority.run();

		System.out.println("PriorityJobScheduler NUM JOBS COMPLETED "
				+ priority.getNumJobsCompleted() + " TOTAL TIME "
				+ priority.getTotalTime());
		
		JobScheduler rrScheduler = new RoundRobinJobScheduler(new ArrayList<Job>(jobs));
		rrScheduler.run();

		System.out.println("RoundRobinJobScheduler NUM JOBS COMPLETED "
				+ rrScheduler.getNumJobsCompleted() + " TOTAL TIME "
				+ rrScheduler.getTotalTime());
		
		JobScheduler selfishRR = new SelfishRRJobScheduler(new ArrayList<Job>(jobs));
		selfishRR.run();

		System.out.println("SelfishRoundRobinJobScheduler NUM JOBS COMPLETED "
				+ selfishRR.getNumJobsCompleted() + " TOTAL TIME "
				+ selfishRR.getTotalTime());
		
		
		JobScheduler deadline = new DeadlineJobScheduler(new ArrayList<Job>(jobs));
		deadline.run();

		System.out.println("DeadlineJobScheduler NUM JOBS COMPLETED "
				+ deadline.getNumJobsCompleted() + " TOTAL TIME "
				+ deadline.getTotalTime());

		JobScheduler shortest = new ShortestProcessFirstJobScheduler(new ArrayList<Job>(jobs));
		shortest.run();

		System.out.println("ShortestProcessFirstJobScheduler NUM JOBS COMPLETED "
				+ shortest.getNumJobsCompleted() + " TOTAL TIME "
				+ shortest.getTotalTime());

	}
}
