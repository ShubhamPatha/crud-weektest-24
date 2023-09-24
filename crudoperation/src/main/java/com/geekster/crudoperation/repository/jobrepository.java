package com.geekster.crudoperation.repository;


import com.geekster.crudoperation.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface jobrepository extends CrudRepository<Job,Integer>
{

    List<Job> findById(Long id);
    Job getAllJobById(Long id);

    }
