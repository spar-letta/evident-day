/**
 * 
 */
package com.simiyu.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.simiyu.model.Job;
import com.simiyu.model.JobApplicant;
import com.simiyu.repository.JobRepository;
import com.simiyu.service.JobApplicantService;
import com.simiyu.service.JobService;
import com.sipios.springsearch.anotation.SearchSpec;
/**
 * @author enock
 *
 */
@RestController
@RequestMapping(value="/job")
public class JobController {

	
	private JobApplicantService jobApplicantService;	
	
	private JobService jobService;
	private JobRepository jobRepository;
	
	@Autowired
	public JobController(JobApplicantService jobApplicantService, JobService jobService, JobRepository jobRepository) {
		this.jobApplicantService = jobApplicantService;
		this.jobService = jobService;
		this.jobRepository = jobRepository;
	}
	
	@PostMapping(value = "/create_job")
	public Job createJob(@RequestBody Job newJob){
		return jobService.createJob(newJob);
	}
//	@GetMapping(value="/seach_job")
//	public List<Job> getAllJobs(@SearchSpec Specification<Job> spec){
//		return jobRepository.findAll(Specification.where(spec));
//	}
//	
	@GetMapping(value="/seach")
	public List<Job> getb(@RequestParam(defaultValue = "empty") String interview_date, @RequestParam(defaultValue = "empty") String job_type){
			if(!interview_date.equals("empty")) {
				return jobService.find_all_jobs(interview_date);
			}else if(!job_type.equals("empty")){
				return jobService.find_all_jobs(job_type);
			}
			return jobRepository.findAll();
		
	}

	//participants for a given job
	@GetMapping(value="/get_participants_by_jobId/{jobUUID}")
	public Set<JobApplicant> findJobById(@PathVariable UUID jobUUID) {
		Set<JobApplicant> xyz = jobService.findByUuid(jobUUID).getApplicant();		
		return xyz;	
	
	}
}
