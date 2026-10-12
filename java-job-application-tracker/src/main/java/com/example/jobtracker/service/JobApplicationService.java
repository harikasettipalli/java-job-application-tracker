package com.example.jobtracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jobtracker.model.JobApplication;
import com.example.jobtracker.repository.JobApplicationRepository;

@Service
@Transactional
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<JobApplication> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public JobApplication findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job application not found: " + id));
    }

    public JobApplication save(JobApplication application) {
        return repository.save(application);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
