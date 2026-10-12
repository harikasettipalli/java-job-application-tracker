package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jobtracker.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
