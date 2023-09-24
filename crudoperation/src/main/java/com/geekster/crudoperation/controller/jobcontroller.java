package com.geekster.crudoperation.controller;

import com.geekster.crudoperation.model.Job;
import com.geekster.crudoperation.model.Type;
import com.geekster.crudoperation.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class jobcontroller {
    @Autowired
    JobService jobService;
    @GetMapping("jobs")
    public Iterable<Job> getAllj(){

        return jobService.getalljob();
    }



    // add a particular room
    @PostMapping("job")
    public void addRoom(@RequestBody Job job){
       jobService.addJobs(job);
    }


    //----------------------------------------------------------------------------------------
    // get particular room by id
    @GetMapping("job/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    //check room is present or not in the database
    @GetMapping("job/{id}/exists")
    public boolean checkRoomExists(@PathVariable Integer id){
        return jobService.checkJobExists(id);
    }

//    //get all the list of rooms by id
//    @GetMapping("rooms/list")
//    public Iterable<HotelRoom> getRoomsByIds(@RequestBody List<Integer> ids){
//        return roomService.getRoomsByIds(ids);
//    }


    // count total no. of rooms present
    @GetMapping("job/count")
    public Integer getTotalRooms(){
        return jobService.getTotalJob();
    }

    // delete a room by id
    @DeleteMapping("jobs/{id}")
    public String deleteRoomById(@PathVariable Integer id){
        return jobService.deleteJobById(id);
    }

    // delete a list of rooms by id

    // delete all the rooms




    //add a room of list
    @PostMapping("jobs")
    public String addRoom(@RequestBody List<Job> rooms){
        return jobService.addjobs(rooms);
    }

    // update room type on the basis of roomId
    @PutMapping("job/{id}/{type}")
    public String updateRoomById(@PathVariable Integer id,@PathVariable Type type){
        return jobService.updateJobById(id,type);
    }

}
