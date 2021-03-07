/**
 * 
 */
package com.simiyu.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.Nullable;

/**
 * @author enock
 *
 */
@Entity
public class JobApplicant {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(length = 16)
 private UUID uuid;
 private long national_id;
 private String first_name;
 private String last_name;
 private String email;
 private String phone;
 private LocalDate date_createDateTime;
 private Education_level education_level;
 private String years_of_experience;
 
 @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
 @JoinTable(name="applicant_job",
 	        joinColumns = {
 	        		@JoinColumn(name="applicant_id", nullable=false, updatable = false)
 			},
 	inverseJoinColumns = 
 	{
	 @JoinColumn(name="job_id", nullable=false, updatable = false)
 	})
 private Set<Job> jobs;
 public JobApplicant() {
	}
 
public JobApplicant(long national_id, String first_name, String last_name, String email, String phone,
		LocalDate date_createDateTime, Education_level education_level, String years_of_experience) {
	this.national_id = national_id;
	this.first_name = first_name;
	this.last_name = last_name;
	this.email = email;
	this.phone = phone;
	this.date_createDateTime = date_createDateTime;
	this.education_level = education_level;
	this.years_of_experience = years_of_experience;
}
public long getNational_id() {
	return national_id;
}

public void setNational_id(long national_id) {
	this.national_id = national_id;
}

public UUID getUuid() {
	return uuid;
}

public void setUuid(UUID uuid) {
	this.uuid = uuid;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public Education_level getEducation_level() {
	return education_level;
}

public void setEducation_level(Education_level education_level) {
	this.education_level = education_level;
}

public String getYears_of_experience() {
	return years_of_experience;
}

public void setYears_of_experience(String years_of_experience) {
	this.years_of_experience = years_of_experience;
}

public LocalDate getDate_createDateTime() {
	return date_createDateTime;
}

public void setDate_createDateTime(LocalDate localDate) {
	this.date_createDateTime = localDate;
}

public Set<Job> getJobs() {
	return jobs;
}

public void setJobs(Set<Job> jobs) {
	this.jobs = jobs;
} 
 


}
