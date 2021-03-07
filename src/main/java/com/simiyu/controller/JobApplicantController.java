/**
 * 
 */
package com.simiyu.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simiyu.exception.JobConflictException;
import com.simiyu.exception.JobhasMaximumParticitantsException;
import com.simiyu.exception.MaximumJobsAppliedException;
import com.simiyu.model.ENUM_JOB_TYPE;
import com.simiyu.model.Job;
import com.simiyu.model.JobApplicant;
import com.simiyu.service.JobApplicantService;
import com.simiyu.service.JobService;
import com.simiyu.util.Util;
import com.simiyu.util.UtilComponent;

/**
 * @author enock
 *
 */
@RestController
@RequestMapping(value = "/applicant")
public class JobApplicantController {

	private JobApplicantService jobApplicantService;	
	
	private JobService jobService;
	
	private UtilComponent utilComponent;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(UtilComponent.class);
	@Autowired
	public JobApplicantController(JobApplicantService jobApplicantService, JobService jobService, UtilComponent utilComponent) {
		this.jobApplicantService = jobApplicantService;
		this.jobService = jobService;
		this.utilComponent = utilComponent;
	}
	
	// create
	@PostMapping(value="/new_job_applicant")
	public ResponseEntity<JobApplicant> createJobAplicant(@RequestBody JobApplicant requestJobApp){
		return new ResponseEntity<>(jobApplicantService.save_jobapplicant(requestJobApp),HttpStatus.CREATED);		
	}
	
	//read all
	@GetMapping(value="/all_applicants")
	public ResponseEntity<List<JobApplicant>> readAllApplicants(){
		return  ResponseEntity.ok(jobApplicantService.findAllJobApplicants());
	}
	
	//update record
	@PutMapping(value="/update_applicant")
	public ResponseEntity<JobApplicant> updatJobApplicant(@PathVariable UUID id, @RequestBody JobApplicant requestJobApp ){
		return ResponseEntity.ok(jobApplicantService.updateJobApplicant(id, requestJobApp));		
	}
	
	//delete
	@DeleteMapping(value = "/delete_applicant")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable long nationalId){
		return new ResponseEntity<Map<String, Boolean>>(jobApplicantService.deleteJobApplicant(nationalId),HttpStatus.OK);
	}
	
	// applicant to select job
	@PutMapping(value = "/select_job/applicant_id/{applicantUuid}/job_id/{jobIUuid}") // 
	public ResponseEntity<JobApplicant> selectJob(@RequestBody JobApplicant jobApplicant , @PathVariable UUID applicantUuid,
			@PathVariable UUID jobIUuid) {
		JobApplicant applicant = jobApplicantService.findOneJobApplicant(applicantUuid);
		Job job = jobService.findByUuid(jobIUuid);
		if(applicant != null && job != null) {		
			if(job.getApplicant().size() < 20) {
					if(!utilComponent.isMaximumJobPerDay(applicantUuid, job.getInterview_date())) {
						if(utilComponent.isConflict(applicantUuid, job.getInterview_date(), job.getInterview_start_time(), job.getInterview_end_time())) {
							applicant.getJobs().add(job);
							jobApplicantService.save_jobapplicant(applicant);
						}else {
							throw new JobConflictException("oops !! conflict occured");
						}
			}else {
				throw new MaximumJobsAppliedException("Reached Maximum Interviews for date " + job.getInterview_date());
				}
			}else {
				throw new JobhasMaximumParticitantsException("Oops !!! Job has maxmum number of participants");
			}
		}
		return ResponseEntity.ok(applicant);
		
		
	}
	
	//deselect selected job
	@DeleteMapping(value = "/deselect_job/applicant_id/{applicantId}/job_id/{jobId}")
	public void deselect_Job_by_job_uuid(@PathVariable UUID applicantId, @PathVariable UUID jobId) {
		JobApplicant applicant = jobApplicantService.findOneJobApplicant(applicantId);
		Job job = jobService.findByUuid(jobId);
		if(applicant != null && job != null) {
			for(JobApplicant jp : job.getApplicant()) {
				jp.getJobs().remove(job);
				jobApplicantService.save_jobapplicant(applicant);
			}
		}
	}
	
	@GetMapping(value = "/one/{uuid}")
	public JobApplicant getApplicantById(@PathVariable UUID uuid) {
		JobApplicant oneApplicant = jobApplicantService.findOneJobApplicant(uuid);
		return oneApplicant;
	}

}
