package com.practo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE_PROFILE")
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_PROFILE_ID",nullable = false)
    private Long empProfileId;

    @Column(name = "EMP_EMPLOYEE_ID",nullable = true)
    private Long empEmployeeId;

    @Column(name = "EMP_QUALIFICATION")
    private String empQualification;

    @Column(name = "EMP_PROFILE_DESCRIPTION")
    private String empDescription;
}
