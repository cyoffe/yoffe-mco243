package yoffe.scheduler;

public class Job {
	private Priority priority;
	private Priority dynamicPriority;

	private JobType type;
	private String name;
	private JobState state;
	private Integer timeLeftToRun;
	private Integer estimatedRunTime;
	private long lastRanAtTime;
	private Long deadline;

	public Job(Priority priority, JobType type, String name, int timeLeftToRun,
			int estimatedRunTime, Long deadline) {
		super();
		this.priority = priority;
		this.name = name;
		this.timeLeftToRun = timeLeftToRun;
		this.type = type;
		this.estimatedRunTime = estimatedRunTime;
		this.deadline = deadline;
	}

	public Priority getPriority() {
		return priority;
	}

	public Priority getDynamicPriority() {
		return dynamicPriority;
	}

	public String getName() {
		return name;
	}

	public JobState getState() {
		return state;
	}

	public Integer getTimeLeftToRun() {
		return timeLeftToRun;
	}

	public long getLastRanAtTime() {
		return lastRanAtTime;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setDynamicPriority(Priority dynamicPriority) {
		this.dynamicPriority = dynamicPriority;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setState(JobState state) {
		this.state = state;
	}

	public void setTimeLeftToRun(int timeLeftToRun) {
		this.timeLeftToRun = timeLeftToRun;
	}

	public void setLastRanAtTime(long lastRanAtTime) {
		this.lastRanAtTime = lastRanAtTime;
	}

	public JobType getType() {
		return type;
	}

	public void decrementTimeLeftToRun(int time) {
		this.timeLeftToRun -= time;
	}

	public boolean isFinished() {
		return this.timeLeftToRun == 0;
	}

	public Integer getEstimatedRunTime() {
		return estimatedRunTime;
	}

	public Long getDeadline() {
		return deadline;
	}

}
