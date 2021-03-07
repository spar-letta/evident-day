/**
 * 
 */
package com.simiyu.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simiyu.exception.ResourceNotFoundException;
import com.simiyu.model.JobApplicant;
import com.simiyu.repository.JobApplicantRepository;
import com.simiyu.service.JobApplicantService;

/**
 * @author enock
 *
 */
@Service
public class JobApplicantServiceImpl implements JobApplicantService{

	@Autowired
	private JobApplicantRepository jobApplicantRepository;

	@Override
	public JobApplicant save_jobapplicant(JobApplicant jobApplicant) {
		jobApplicant.setDate_createDateTime(LocalDate.now());
		return 	jobApplicantRepository.save(jobApplicant);
	}

	@Override
	public List<JobApplicant> findAllJobApplicants() {
		return jobApplicantRepository.findAll();
	}

	@Override
	public JobApplicant findOneJobApplicant(UUID uuid) {
		JobApplicant jobApplicant = jobApplicantRepository.findOneJobApplicant(uuid);
		if(jobApplicant != null) {
			return jobApplicant;
			 
		}else {
		 throw new ResourceNotFoundException("No such Job Applicant with uuid " + jobApplicant.getUuid());
	}}

	@Override
	public JobApplicant updateJobApplicant(UUID uuid, JobApplicant jobA) {
		JobApplicant ja = jobApplicantRepository.getOne(uuid);
		JobApplicant updateJobApplicant = new JobApplicant();
		if(ja !=null) {	
			updateJobApplicant.setFirst_name(jobA.getFirst_name());
			updateJobApplicant.setLast_name(jobA.getLast_name());
			updateJobApplicant.setPhone(jobA.getPhone());
			updateJobApplicant.setEducation_level(jobA.getEducation_level());
			updateJobApplicant.setDate_createDateTime(jobA.getDate_createDateTime());
			updateJobApplicant.setYears_of_experience(jobA.getYears_of_experience());
			final JobApplicant updatedJobApplicant = jobApplicantRepository.save(updateJobApplicant);
			return updatedJobApplicant;
		}else {
			throw new ResourceNotFoundException("No such Job Applicant with uuid " + ja.getUuid());
		}
		
	}

	@Override
	public Map<String, Boolean> deleteJobApplicant(long national_id) {
		JobApplicant ja = jobApplicantRepository.findById(national_id);
		if(ja == null ) {
			throw new ResourceNotFoundException("No such Job Applicant with uuid " + ja.getUuid());
		}else {
			jobApplicantRepository.delete(ja);
			Map<String, Boolean> response = new HashMap<>();
			response.put("Deleted Job Applicant with uuid " + ja.getUuid(), true);
			return response;
		}
		
	}

	@Override
	public void deleteAllJobApplicant() {
		jobApplicantRepository.deleteAll();
		 
	}
	
	
}
