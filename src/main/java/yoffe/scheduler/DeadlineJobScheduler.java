package yoffe.scheduler;

import java.util.ArrayList;

public class DeadlineJobScheduler extends JobScheduler {

	public DeadlineJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new DeadlineComparator());
		
	}

}
