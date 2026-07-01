package com.jobportal.jobportalbackend.controller;

import com.jobportal.jobportalbackend.entity.Job;
import com.jobportal.jobportalbackend.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "https://jobportal-frontend-h7pf.onrender.com",
        "http://localhost:3000"
})
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
    @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable Long id)
    {
        return jobService.getJobById(id);
    }
    @PostMapping("/jobs")
    public Job createJob(@RequestBody Job job)
    {
        return jobService.createJob(job);
    }
    @PutMapping("/jobs/{id}")
    public Job updateJob(@PathVariable Long id,@RequestBody Job updatedJob)
    {
        return jobService.updateJob(id,updatedJob);
    }
    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Long id)
    {
        jobService.deleteJob(id);
    }
    @PostMapping("/jobs/bulk")
    public List<Job> createJobs(@RequestBody List<Job> jobs)
    {
        return jobService.createJobs(jobs);
    }

}
