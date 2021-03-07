/**
 * 
 */
package com.simiyu.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simiyu.model.Job;
import com.simiyu.model.JobApplicant;
import com.simiyu.service.JobApplicantService;
import com.simiyu.service.JobService;

/**
 * @author enock
 *
 */
@Component
public class Util {

	@Autowired
	private JobService jobService;
	@Autowired
	private JobApplicantService jobApplicantService;
	// enable participants select jobs interviews to participate in
	
	public boolean isjobConflict(UUID uuid, Date interview_date) {
		// 3 interviews per day   ==========   how many interviews a participant has enrolled in that given day
		JobApplicant yyyApplicant = jobApplicantService.findOneJobApplicant(uuid);
		int count = 0;
		if(yyyApplicant != null) {
			for(Job job : yyyApplicant.getJobs()) {
				if(job.getInterview_date() == interview_date) {
					count++;
						
				}
				
			}
			
		}
		
		return (count >= 3) ? true : false;
		 
		
	}
	
	
	
	
	
	
	
	
	
	
//	public JobApplicant selecteJob(UUID jobIUuid, UUID applicantUuid) {
//		JobApplicant applicant = jobApplicantService.findOneJobApplicant(applicantUuid);
//		if(applicant != null) {
//			Job job = jobService.findByUuid(jobIUuid);
//			if(job != null) {
//				applicant.setJobs((List<Job>) job);
//				
//			}
//		}
//		return jobApplicantService.save_jobapplicant(applicant);	
//		
//	}
//	
//	// deselect job
//	public List<Job> deselect_Job(UUID jobIUuid,UUID applicantUuid){
//		JobApplicant applicant = jobApplicantService.findOneJobApplicant(applicantUuid);
//		List<Job> list_jobs_selectedJobs = new ArrayList<>();
//		if(applicant !=null) {
//			Job selected_Job = jobService.findByUuid(jobIUuid);
//			jobService.delete_job(selected_Job);
//			list_jobs_selectedJobs = jobService.find_All_jobs();
//			
//		}
//		return list_jobs_selectedJobs;
//	}
//	
//	// jobs selected by an applicant
//	public List<Job> selected_jobs(UUID applicantUuid ){
//		List<Job> list_applicants_jobs = new ArrayList<>();
//		JobApplicant isApplicantPresent = jobApplicantService.findOneJobApplicant(applicantUuid);
//		if(isApplicantPresent != null) {
//			list_applicants_jobs = jobService.select_job(applicantUuid);
//		}
//		return list_applicants_jobs;
//	}
//	
//	// participants for a given job
//	public List<JobApplicant> listParticipants(UUID jobIUuid){
//		List<JobApplicant> participants = new ArrayList<>();
//		Job job  = jobService.findByUuid(jobIUuid);
//			if(job !=null) {
//				 participants = job.getApplicant();				
//			}
//			return participants;
//		
//	}
}
