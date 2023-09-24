
# week test crud operation
## FRAMEWORK AND LANGUAGE USED
* JAVA 17
* MAVEN
* SPRINGBOOT 3.1.1
* h2 database
<!-- Headings -->   
## DATA FLOW

<!-- Code Blocks -->

### CONTROLLER
  ``` 
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
```


 ### MODEL
  #### job
  ``` 
package com.geekster.crudoperation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOB")

public class Job {

    @Id
    private Integer id;
    private String title;
    private String description;
    private String location;
    @Min(value =20000)
    private Double salary;
    private String companyEmail;
    private String companyName;
    private String employerName;
    private Type jobType;
    private LocalDate appliedDate;

}

```
#### Type
  ```
package com.geekster.crudoperation.model;

public enum Type {
    IT,
    HR ,
    Sales,
    Marketing,
}

```


### REPO
  ``` 
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


```


### SERVICE
  ``` 
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

```


### MAIN
  ``` 
package com.geekster.crudoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudoperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudoperationApplication.class, args);
	}

}



```

 ### Application Properties
  ```

spring.datasource.url = jdbc:h2:mem:h2db
spring.datasource.driverClassName = org.h2.Driver
spring.datasource.userName = shubham
spring.datasource.password = shubham
spring.jpa.database-platform = org.hibernate.dialect.H2Dialect
spring.h2.console.enabled = true


spring.jpa.properties.hibernate.show_sql = true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

#EnableH2Console-http://localhost:8080/h2-console

```


 ### POM
  ``` 
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.geekster</groupId>
	<artifactId>UserManagement</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>UserManagement</name>
	<description>user management to study validations</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```
## DATA STRUCTURE USED
* LIST 
* STRING
* LOCAL DATE
* INTEGER
* USER

# PROJECT SUMMARY

## USER MANAGEMENT SYSTEM Has been created with the following attribute

* UserId
* username
* DateOfBirth
* Email
* Phone Number
* Date 
* Time
### Endpoint to be provided 
* AddUser 
* getUser/{userid}
* getAllUser
* updateUserInfo
* deleteUser









<!-- Headings -->   
# Author
SHUBHAM PATHAK
 <!-- UL -->
* Twitter <!-- Links -->
[@ShubhamPathak]( https://twitter.com/Shubham15022000)
* Github  <!-- Links -->
[@ShubhamPathak]( https://github.com/ShubhamPatha)
<!-- Headings -->   
