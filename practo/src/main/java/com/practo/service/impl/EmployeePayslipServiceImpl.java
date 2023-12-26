package com.practo.service.impl;

import com.practo.entity.Employee;
import com.practo.entity.EmployeeAttendance;
import com.practo.entity.EmployeePayslip;
import com.practo.repository.EmployeeAttendanceRepository;
import com.practo.repository.EmployeePayslipRepository;
import com.practo.repository.EmployeeRepository;
import com.practo.service.EmployeePayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@Service
public class EmployeePayslipServiceImpl implements EmployeePayslipService {
        @Autowired
        private EmployeeRepository employeeRepository;
        @Autowired
        private EmployeeAttendanceRepository employeeAttendanceRepository;
        @Autowired
        private EmployeePayslipRepository employeePayslipRepository;

    @Async
    public CompletableFuture<EmployeePayslip> generatePayslip(Long employeeId, LocalDate startDate, LocalDate endDate) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {

            // Handle employee not found
            return CompletableFuture.completedFuture(null);
        }

        List<EmployeeAttendance> attendances = employeeAttendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate.atStartOfDay(), endDate.atStartOfDay());
        int numberOfDaysPresent = attendances.size();

        // Calculate salary based on attendance, or any other logic
        double salary = calculateSalary(employee, numberOfDaysPresent);

        EmployeePayslip payslip = new EmployeePayslip();
        payslip.setEmployee(employee);
        payslip.setEpsCurrentTime(LocalDateTime.now());
        payslip.setEpsAmount(salary);

        // Save the generated payslip
        EmployeePayslip savedPayslip = employeePayslipRepository.save(payslip);
        return CompletableFuture.completedFuture(savedPayslip);
    }

    // Implementing calculation logic
    private double calculateSalary(Employee employee, int numberOfDaysPresent) {

        return numberOfDaysPresent * employee.getEmpSalary();
    }


//

}

































//        @Async
//        public void calculateAndSaveMonthlySalaryForEmployee(Long empId) {
//            // Fetch employee by ID
//            Employee employee = employeeRepository.findById(empId).orElse(null);
//
//            if (employee != null) {
//                // Fetch employee's attendance records for the month
//                LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
//                LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);
//
//                List<EmployeeAttendance> attendanceList = employeeAttendanceRepository
//                        .findByEmployeeIdAndDateBetween(empId, startOfMonth, endOfMonth);
//
//
//                // Calculate monthly salary based on attendance
//                double monthlySalary = calculateSalaryFromAttendance(attendanceList, employee.getEmpSalary());
//
//                // Create Payslip entity and save
//                EmployeePayslip payslip = new EmployeePayslip();
//                payslip.setCurrentTime(LocalDateTime.now());
//                payslip.setAmount(monthlySalary);
//                payslip.setEmployee(employee);
//                employeePayslipRepository.save(payslip);
//            }
//        }
//
//        // Method to calculate salary based on attendance
//        private double calculateSalaryFromAttendance(List<EmployeeAttendance> attendanceList, double baseSalary) {
////            int presentDays = 0;
////            for (EmployeeAttendance attendance : attendanceList) {
////                if (attendance.getAttendanceStatus() == EmployeeAttendanceStatus.PRESENT) {
////                    presentDays++;
////                }
////            }
////            // Assuming 20 working days in a month for simplicity
////            return baseSalary * (presentDays / 20.0);
//
//            long presentDays = attendanceList.stream()
//                    .filter(attendance -> attendance.getAttendanceStatus() == EmployeeAttendanceStatus.PRESENT)
//                    .count();
//
//            // Assuming a full month period (from start to end of the month)
//            LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
//            LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);
//            long totalDaysInMonth = ChronoUnit.DAYS.between(startOfMonth, endOfMonth) + 1;
//
//            // Calculate salary proportionally based on present days
//            double salary = baseSalary * (presentDays / (double) totalDaysInMonth);
//            return salary;
//        }

