package project4;

import java.util.LinkedList;

public interface ScheduleMetric {
	
	/**
	 * Interface for Scheduling Job
	 * @param schedule
	 * @param job
	 * @return true if the job schedules
	 */
	public boolean scheduleJob (LinkedList<ScheduleSlot> schedule, Job job); 
	
}
