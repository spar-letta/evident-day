/**
 * 
 */
package com.simiyu.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author enock
 *
 */
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 16)
	private UUID uuid;
	private String name;
	private String descriptionString;
	@Enumerated(EnumType.STRING)
	private ENUM_JOB_TYPE jobType;
	private String years_of_experienceString;
	@Enumerated(EnumType.STRING)
	private ENUM_ED_LEVEL education_level;
	@Enumerated(EnumType.STRING)
	private ENUM_STATUS status;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(unique = false)
	private Date interview_date;
	//@Temporal(TemporalType.TIME)
	@JsonFormat(pattern="HH:mm:ss")
	private Time interview_start_time;
	@JsonFormat(pattern="HH:mm:ss")
	private Time interview_end_time;
	
	
	@ManyToMany(mappedBy = "jobs", fetch = FetchType.LAZY, cascade = CascadeType.ALL ) //cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private Set<JobApplicant> applicant;
	public Job() {
		
	}

	public Job(String name, String descriptionString, ENUM_JOB_TYPE jobType, String years_of_experienceString,
			ENUM_ED_LEVEL education_level, ENUM_STATUS status, Date interview_date, Time interview_start_time,
			Time interview_end_time) {
		this.name = name;
		this.descriptionString = descriptionString;
		this.jobType = jobType;
		this.years_of_experienceString = years_of_experienceString;
		this.education_level = education_level;
		this.status = status;
		this.interview_date = interview_date;
		this.interview_start_time = interview_start_time;
		this.interview_end_time = interview_end_time;
	}
	
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public ENUM_JOB_TYPE getJobType() {
		return jobType;
	}

	public void setJobType(ENUM_JOB_TYPE jobType) {
		this.jobType = jobType;
	}

	public String getYears_of_experienceString() {
		return years_of_experienceString;
	}

	public void setYears_of_experienceString(String years_of_experienceString) {
		this.years_of_experienceString = years_of_experienceString;
	}

	public ENUM_ED_LEVEL getEducation_level() {
		return education_level;
	}

	public void setEducation_level(ENUM_ED_LEVEL education_level) {
		this.education_level = education_level;
	}

	public ENUM_STATUS getStatus() {
		return status;
	}

	public void setStatus(ENUM_STATUS status) {
		this.status = status;
	}

	public Date getInterview_date() {
		return interview_date;
	}

	public void setInterview_date(Date interview_date) {
		this.interview_date = interview_date;
	}

	public Time getInterview_start_time() {
		return interview_start_time;
	}

	public void setInterview_start_time(Time interview_start_time) {
		this.interview_start_time = interview_start_time;
	}

	public Time getInterview_end_time() {
		return interview_end_time;
	}

	public void setInterview_end_time(Time interview_end_time) {
		this.interview_end_time = interview_end_time;
	}
	
	public Set<JobApplicant> getApplicant() {
		return applicant;
	}

	public void setApplicant(Set<JobApplicant> applicant) {
		this.applicant = applicant;
	}

	
	
}
