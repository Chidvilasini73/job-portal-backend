package com.jobportal.jobportalbackend.controller;

import com.jobportal.jobportalbackend.entity.Application;
import com.jobportal.jobportalbackend.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
//@RequestMapping("/applications")
@CrossOrigin(origins = {
        "https://jobportal-frontend-h7pf.onrender.com",
        "http://localhost:3000"
})


public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService)
    {
        this.applicationService = applicationService;
    }

    @GetMapping("/applications")
    public List<Application> getAllApplications()
    {
        return applicationService.getAllApplications();
    }
    @PostMapping("/applications")
    public ResponseEntity<Application> createApplication(@RequestBody Application application)
    {
        Application savedApplication = applicationService.createApplication(application);

        if (savedApplication == null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.ok(savedApplication);
    }
    @GetMapping("/applications/user/{userId}")
    public List<Application> getApplicationsByUserId(@PathVariable Long userId)
    {
        return applicationService.getApplicationsByUserId(userId);
    }
    @GetMapping("/applications/job/{jobId}")
    public List<Application> getApplicationsByJobId(@PathVariable Long jobId)
    {
        return applicationService.getApplicationsByJobId(jobId);
    }
    @PutMapping("/applications/{id}/status")
    public Application updateStatus(
            @PathVariable Long id,
            @RequestParam String status)
    {
        return applicationService.updateStatus(id, status);
    }
}