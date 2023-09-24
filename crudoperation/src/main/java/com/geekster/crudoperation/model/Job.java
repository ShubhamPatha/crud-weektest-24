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
