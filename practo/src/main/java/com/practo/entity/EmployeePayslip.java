package com.practo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE_PAYSLIP")
public class EmployeePayslip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EPS_ID")
    private Long epsId;

//    @Column(name = "EPS_EMPLOYEE_ID", insertable = false, updatable = false)
//    private Long epsEmployeeId;
//
//    @Column(name = "EPS_START_DATE")
//    private LocalDate epsStartDate;
//
//    @Column(name = "EPS_END_DATE")
//    private LocalDate epsEndDate;
//
//    @Column(name = "EPS_SALARY")
//    private double epsSalary;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "EPS_EMPLOYEE_ID")
//    private Employee employee;

   @Column(name = "EPS_TIME")
    private LocalDateTime epsCurrentTime;

    @Column(name = "EPS_AMOUNT")
    private double epsAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMP_ID")
    private Employee employee;

}
