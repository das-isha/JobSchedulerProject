package project4;

/**
 * @author Isha Das
 * This class represents a ScheduleSlot 
 */
public class ScheduleSlot {
	
	 // a field to store the Job
	private Job job;
	// a field to store the startTime
	private int startTime;

	/* A constructor to make a ScheduleSlot with given job and startTime */
	public ScheduleSlot(Job job, int startTime) {
		this.job = job;
		this.startTime = startTime;
	}
	 /* get the job of the ScheduleSlot */
	Job getJob() {
		return job;
	}
	
	 /* set the job of the ScheduleSlot */
	public void setJob(Job job) {
		this.job = job;
	}
	
	 /* get the startTime of the ScheduleSlot */
	public int getStartTime() {
		return startTime;
	}
	
	 /* set the job of the ScheduleSlot */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

}
