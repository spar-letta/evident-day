/**
 * 
 */
package com.simiyu.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simiyu.model.JobApplicant;

/**
 * @author enock
 *
 */
public interface JobApplicantRepository extends JpaRepository<JobApplicant, UUID>{

	@Query(value = "SELECT * FROM job_applicant ja WHERE ja.national_id = ?1", 
			nativeQuery = true
			)
	JobApplicant findById(long national_id);

	@Query(value = "SELECT * FROM job_applicant j WHERE j.uuid = ?1", 
			nativeQuery = true
			)
	JobApplicant findOneJobApplicant(UUID uuid);
	
	//Optional<JobApplicant> findOneJobApplicant(UUID uuid);
}
