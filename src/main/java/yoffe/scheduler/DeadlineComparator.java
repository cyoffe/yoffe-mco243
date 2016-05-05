package yoffe.scheduler;

import java.util.Comparator;

public class DeadlineComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		return a.getDeadline().compareTo(b.getDeadline());
	}

}
