package com.jobportal.jobportalbackend.service;

import com.jobportal.jobportalbackend.entity.Application;
import com.jobportal.jobportalbackend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository)
    {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllApplications()
    {
        return applicationRepository.findAll();
    }
    public Application createApplication(Application application)
    {
        Long userId = application.getUser().getId();
        Long jobId = application.getJob().getId();

        boolean alreadyApplied =
                applicationRepository.existsByUserIdAndJobId(userId, jobId);

        if (alreadyApplied)
        {
            return null;
        }

        return applicationRepository.save(application);
    }
    public List<Application> getApplicationsByUserId(Long userId)
    {
        return applicationRepository.findByUserId(userId);
    }
    public List<Application> getApplicationsByJobId(Long jobId)
    {
        return applicationRepository.findByJobId(jobId);
    }
    public Application updateStatus(Long id, String status)
    {
        Application application =
                applicationRepository.findById(id).orElse(null);

        if(application != null)
        {
            application.setStatus(status);
            return applicationRepository.save(application);
        }

        return null;
    }
}