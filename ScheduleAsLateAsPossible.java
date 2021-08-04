package project4;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Isha Das
 * This class implements the ScheduleMetric interface and adds job to the Schedule as late as possible
 */
public class ScheduleAsLateAsPossible implements ScheduleMetric {

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

	/**
	 * @param schedule
	 * @param job
	 * @return whether the job is scheduled 
	 */
	private boolean scheduleJobType(LinkedList<ScheduleSlot> schedule, Job job) {

		//sets the scedule job method to false
		boolean isScheduleJob = false;
		ScheduleSlot newJobSlot = new ScheduleSlot( job, (job.getDeadline() - job.getDuration()) );

		if(schedule.isEmpty()) {
			if( (job.getEarliestStart() + job.getDuration()) <= job.getDeadline()) {
				schedule.addFirst(newJobSlot);
				isScheduleJob = true;
			} else {
				isScheduleJob = false;
			}
		} else {
			int i = schedule.size() - 1;
			// Flag to track if the job has any overlaps with any Slot Job in the schedule
			boolean overlapJobFound = false;
			
			// If Job can't be finised by the deadline
			if( (job.getEarliestStart() + job.getDuration()) > job.getDeadline()) {
				isScheduleJob = false;
				overlapJobFound = true;
			}
			
			// Loop through the schedule LinkedList
			while (i >= 0 && !overlapJobFound) {
				ScheduleSlot sJob = schedule.get(i);
				int slotJobEndTime = sJob.getStartTime() + sJob.getJob().getDuration();
				int jobStartTime = job.getDeadline() - job.getDuration();

				if( (jobStartTime > sJob.getStartTime() && jobStartTime < slotJobEndTime) 
						|| ( job.getDeadline() < sJob.getStartTime() && job.getDeadline() > slotJobEndTime) ) {
					overlapJobFound = true;
				}
				i--;
			}
			// If no overlap Slot Job found in the schedule, add the Slot Job to schedule 
			if(!overlapJobFound) {
				schedule.add(newJobSlot);
			}
		}
		return isScheduleJob;
	}
}
