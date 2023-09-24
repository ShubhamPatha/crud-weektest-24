package com.geekster.crudoperation.service;

import com.geekster.crudoperation.model.Job;
import com.geekster.crudoperation.model.Type;
import com.geekster.crudoperation.repository.jobrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    jobrepository jobrepo;


    public Iterable<Job> getalljob() {
        return jobrepo.findAll();
    }


    public void addJobs(Job job) {
        jobrepo.save(job);
    }


    public String addjobs(List<Job> job) {
        jobrepo.saveAll(job);
        return "jobs added"+job;
    }

    public Job getJobById(Long id) {
        return jobrepo.getAllJobById(id);
    }

    public boolean checkJobExists(Integer id) {
        return jobrepo.existsById(id);
    }

    public Iterable<Job> getJobByIds(List<Integer> ids) {
        return jobrepo.findAllById(ids);
    }

    public Integer getTotalJob() {
        return (int) jobrepo.count();
    }

    public String deleteJobById(Integer id) {
      jobrepo.deleteById(id);
        return "job deleted successfully of id "+id;
    }

    public String updateJobById(Integer id, Type type) {
        Optional<Job> myJobOpt = jobrepo.findById(id);
        Job myJob = null;
        if(myJobOpt.isPresent())
        {
            myJob = myJobOpt.get();
        }
        else {
            return "Id not Found!!!";
        }
        myJob.setJobType(type);
        jobrepo.save(myJob);
        return "room type updated";

    }





}
