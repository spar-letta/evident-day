/**
 * 
 */
package com.simiyu.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simiyu.model.Job;

/**
 * @author enock
 *
 */
@RepositoryRestResource
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job>{

	@Query(value = "SELECT * FROM job j WHERE j.interview_date LIKE %?1% OR j.job_type LIKE %?1% "
			, nativeQuery = true)
	public List<Job> find_all_jobs(String keyword);
	
	@Query(value = "select * from job j where j.uuid = ?1", nativeQuery = true
			)
	List<Job> getOne(UUID uuid);
	
	@Query(value = "select * from job j where j.uuid = ?1", nativeQuery = true
			)
	Job findByUuid(UUID jobUUID);
	
	

}
