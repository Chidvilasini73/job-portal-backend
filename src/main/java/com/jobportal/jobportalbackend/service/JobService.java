package com.jobportal.jobportalbackend.service;

import com.jobportal.jobportalbackend.entity.Job;
import com.jobportal.jobportalbackend.repository.JobRepository;
import org.springframework.stereotype.Service;
import com.jobportal.jobportalbackend.repository.ApplicationRepository;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public JobService(JobRepository jobRepository,
                      ApplicationRepository applicationRepository) {

        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    public Job getJobById(Long id)
    {
        return jobRepository.findById(id).orElse(null);
    }
    public Job createJob(Job job)
    {
        return jobRepository.save(job);
    }
    public Job updateJob(Long id,Job updatedJob)
    {
        Job job=jobRepository.findById(id).orElse(null);
        if(job!=null)
        {
            job.setTitle(updatedJob.getTitle());
            job.setCompany(updatedJob.getCompany());
            job.setLocation(updatedJob.getLocation());
            job.setSalary(updatedJob.getSalary());
            job.setDescription(updatedJob.getDescription());
            return jobRepository.save(job);
        }
        return null;
    }
    public void deleteJob(Long id)
    {
        applicationRepository.deleteByJobId(id);
        jobRepository.deleteById(id);
    }
    public List<Job> createJobs(List<Job> jobs)
    {
        return jobRepository.saveAll(jobs);
    }
}