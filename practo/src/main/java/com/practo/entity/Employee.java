package com.practo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employeeDetails")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;
    @Column(name = "first_Name")
    private String firstName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "emp_email")
    private String email;
    @Column(name = "emp_department")
    private String department;
    @Column(name = "emp_salary")
    private double salary;
    @Column(name = "emp_address")
    private String address;
    @Column(name = "emp_phoneNumber")
    private String phoneNumber;

}
