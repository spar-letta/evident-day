/**
 * 
 */
package com.simiyu.util;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simiyu.model.Job;
import com.simiyu.model.JobApplicant;
import com.simiyu.service.JobApplicantService;
import com.simiyu.service.JobService;
import com.sun.el.stream.Stream;

import ch.qos.logback.classic.Logger;

/**
 * @author enock
 *
 */
@Component
public class UtilComponent {

	@Autowired
	private JobService jobService;
	@Autowired
	private JobApplicantService jobApplicantService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UtilComponent.class);
	// enable participants select jobs interviews to participate in
	
	public boolean isMaximumJobPerDay(UUID uuid, Date interview_date) {
		// 3 interviews per day   ==========   how many interviews a participant has enrolled in that given day
		JobApplicant yyyApplicant = jobApplicantService.findOneJobApplicant(uuid);
		int count = 0;
		if(yyyApplicant != null){
			for(Job job : yyyApplicant.getJobs()){
				if(job.getInterview_date() == interview_date){
					count++;						
				}				
			}			
		}		
		return (count >= 3) ? true : false;
	}
	
	public boolean isConflict(UUID applicaUuid, Date interview_date, Time interview_starTime, Time interview_endTime){
		boolean isJobConflict = true;
		JobApplicant yyyApplicant = jobApplicantService.findOneJobApplicant(applicaUuid);
		if(yyyApplicant != null){			
			for(Job job : yyyApplicant.getJobs()){	// ||interview_endTime.before(job.getInterview_start_time())
			 if((job.getInterview_start_time().after(interview_endTime)) || (job.getInterview_end_time().before(interview_starTime))) {//|| (job.getInterview_start_time() != interview_starTime)){
								
					return isJobConflict= false;
				}else {
					break;
				}
				}
			}
		
		return isJobConflict;
	}
	}

