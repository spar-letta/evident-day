/**
 * 
 */
package com.simiyu.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.simiyu.model.JobApplicant;
import com.sun.el.stream.Optional;

/**
 * @author enock
 *
 */
public interface JobApplicantService {

	//----------------------------------//CRUD-----------------------------------------------
	
	//----------------------------------//CREATE-----------------------------------------------
	JobApplicant save_jobapplicant(JobApplicant jobApplicant);
	
	//----------------------------------//READ-----------------------------------------------
	List<JobApplicant> findAllJobApplicants();
	
	//----------------------------------//READ ONE-----------------------------------------------
	JobApplicant findOneJobApplicant(UUID uuid);
	
	//----------------------------------//UPDATE-----------------------------------------------	
	JobApplicant updateJobApplicant(UUID uuid, JobApplicant jobApplicant);
	
	//----------------------------------//DELETE ONE-----------------------------------------------	
	 Map<String, Boolean>  deleteJobApplicant(long national_id);
	
	//----------------------------------//DELETE ALL-----------------------------------------------
	void deleteAllJobApplicant();
}
