package yoffe.scheduler;

import java.util.Comparator;

public class ShortestProcessFirstComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		return a.getEstimatedRunTime().compareTo(b.getEstimatedRunTime());
	}

}
