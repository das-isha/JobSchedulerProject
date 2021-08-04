package project4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Isha Das
 * This class implements the ScheduleMetric interface and adds earliest possible slot job to the Schedule
 */
public class ScheduleAsEarlyAsPossible implements ScheduleMetric {

	@Override
	public boolean scheduleJob(LinkedList<ScheduleSlot> schedule, Job job) {
		//sets the scedule job method to false
		boolean isScheduleJob = false;

		if(job instanceof CompoundJob){
			CompoundJob cj = (CompoundJob) job;
			ArrayList<Job> jobs = cj.getSubJobs();
			
			// Loop through the schedule LinkedList
			for(Job sJob: jobs ) {
				isScheduleJob = scheduleJobType(schedule,sJob);
			}			
		}	
		else {			
			isScheduleJob = scheduleJobType(schedule, job);			
		}

		return isScheduleJob;
	}

	private boolean scheduleJobType(LinkedList<ScheduleSlot> schedule, Job job) {

		//sets the scedule job method to false
		boolean isScheduleJob = false;
		ScheduleSlot newJobSlot = new ScheduleSlot(job, job.getEarliestStart());

		if(schedule.isEmpty()) {
			if( (job.getEarliestStart() + job.getDuration()) <= job.getDeadline()) {
				schedule.addFirst(newJobSlot);
				isScheduleJob = true;
			} else {
				isScheduleJob = false;
			}
		} else {
			int i = 0;
			// Flag to track if the job has any overlaps with any Slot Job in the schedule
			boolean overlapJobFound = false;
			
			// If Job can't be finised by the deadline
			if( (job.getEarliestStart() + job.getDuration()) > job.getDeadline()) {
				isScheduleJob = false;
				overlapJobFound = true;
			}
			
			// Loop through the schedule LinkedList
			while (schedule.size() > i && !overlapJobFound) {
				ScheduleSlot sJob = schedule.get(i);
				int slotJobEndTime = sJob.getStartTime() + sJob.getJob().getDuration();
				int jobEndTime = job.getEarliestStart() + job.getDuration();
				//checks to see if an overlap is found and increments on
				if( (job.getEarliestStart() > sJob.getStartTime() && job.getEarliestStart() < slotJobEndTime) 
						|| ( jobEndTime < sJob.getStartTime() && jobEndTime > slotJobEndTime) ) {
					overlapJobFound = true;
				}
				i++;
			}
			// If no overlap Slot Job found in the schedule, add the Slot Job to schedule 
			if(!overlapJobFound) {
				schedule.add(newJobSlot);
			}
		}
		return isScheduleJob;
	}
}


