package yoffe.scheduler;

import java.util.ArrayList;

public class ShortestProcessFirstJobScheduler extends JobScheduler {

	public ShortestProcessFirstJobScheduler(ArrayList<Job> jobs) {
		super(jobs, new ShortestProcessFirstComparator());
	}

}
