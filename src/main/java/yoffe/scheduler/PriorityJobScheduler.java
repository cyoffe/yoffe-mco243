package yoffe.scheduler;

import java.util.ArrayList;

public class PriorityJobScheduler extends JobScheduler {

	public PriorityJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new PriorityJobComparator());
	}

}
