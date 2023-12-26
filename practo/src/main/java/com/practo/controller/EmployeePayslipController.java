package com.practo.controller;

import com.practo.entity.EmployeePayslip;
import com.practo.repository.EmployeePayslipRepository;
import com.practo.service.EmployeePayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/payslip")
public class EmployeePayslipController {

    @Autowired
    private EmployeePayslipService employeePayslipService;

    @PostMapping("/{empId}")
    public ResponseEntity<String> generatePayslipForEmployee(
            @PathVariable Long empId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        CompletableFuture<EmployeePayslip> payslipFuture = employeePayslipService.generatePayslip(empId, startDate, endDate);

        try {
            EmployeePayslip employeePayslip = payslipFuture.get();
            if (employeePayslip != null) {
                return ResponseEntity.ok("Payslip generated for Employee ID: " + empId);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
            }
        } catch (Exception e) {
            // Handle exceptions accordingly
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate payslip.");
        }
    }

//    @PostMapping("/calculate/{employeeId}")
//    public ResponseEntity<String> calculateMonthlySalary(@PathVariable Long employeeId) {
//        employeePayslipService.calculateAndSaveMonthlySalaryForEmployee(employeeId);
//        return ResponseEntity.ok("Monthly salary calculation initiated for employee with ID: " + employeeId);
//    }
}
