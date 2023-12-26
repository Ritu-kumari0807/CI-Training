package com.practo.entity;

import com.practo.dictionary.APIErrorCode;
import com.practo.exception.InspireException;
import com.practo.payload.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE_DETAILS", uniqueConstraints = {@UniqueConstraint(columnNames = {"emp_email"})})
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID",nullable = false)
    private Long empId;

    @Column(name = "EMP_FIRST_NAME")
    private String empFirstName;

    @Column(name = "EMP_LAST_NAME")
    private String empLastName;

    @Column(name = "EMP_EMAIL")
    private String empEmail;

    @Column(name = "EMP_DEPARTMENT")
    private String empDepartment;

    @Column(name = "EMP_SALARY")
    private double empSalary;

    @Column(name = "EMP_ADDRESS")
    private String empAddress;

    @Column(name = "EMP_PHONE")
    private String empPhoneNumber;

    //   One to one mapping
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_EMPLOYEE_ID",referencedColumnName = "EMP_ID")
    private Set<EmployeeProfile> employeeProfile;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeePayslip> employeePayslip;
}



