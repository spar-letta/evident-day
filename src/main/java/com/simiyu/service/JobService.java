/**
 * 
 */
package com.simiyu.service;

import java.util.List;
import java.util.UUID;

import javax.swing.Spring;

import org.springframework.data.jpa.domain.Specification;

import com.simiyu.model.Job;

/**
 * @author enock
 *
 */
public interface JobService {

	Job createJob(Job newJob);
	
	 List<Job> select_job(UUID uuid);
	 
	 Job findByUuid(UUID jobUUID);
	 
	 List<Job> find_all_jobs(String keyword);
	 
	 void delete_job(Job job);
}
