package com.practo.service;

import com.practo.entity.EmployeePayslip;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public interface EmployeePayslipService {
//    void calculateAndSaveMonthlySalaryForEmployee(Long empId);

    CompletableFuture<EmployeePayslip> generatePayslip(Long empId, LocalDate startDate, LocalDate endDate);
}
