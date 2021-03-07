/**
 * 
 */
package com.simiyu.service.impl;

import java.util.List;
import java.util.UUID;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simiyu.model.Job;
import com.simiyu.repository.JobRepository;
import com.simiyu.service.JobService;

/**
 * @author enock
 *
 */
@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public Job createJob(Job newJob) {
		newJob.setApplicant(newJob.getApplicant());
		return jobRepository.save(newJob);
	}

	@Override
	public List<Job> select_job(UUID uuid) {
		return jobRepository.getOne(uuid);
	}

	@Override
	public Job findByUuid(UUID jobUUID) {		
		return jobRepository.findByUuid(jobUUID);
	}

	@Override
	public List<Job> find_all_jobs(String keyword) {
		if(keyword != null) {
			return jobRepository.find_all_jobs(keyword);
		}else {
			return jobRepository.findAll();
		}
		//return jobRepository.findAll(spec);
	}

	@Override
	public void delete_job(Job job) {
		jobRepository.delete(job);
	}

	
}
