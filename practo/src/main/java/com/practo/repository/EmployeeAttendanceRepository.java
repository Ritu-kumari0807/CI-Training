package com.practo.repository;

import com.practo.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    boolean existsByEmployeeId(Long employeeId);

    List<EmployeeAttendance> findByEmployeeIdAndDateBetween(Long employeeId, LocalDateTime localDateTime, LocalDateTime localDateTime1);
//    List<EmployeeAttendance> findByEmaEmployeeIdAndEmaDateBetween(Long emaEmployeeId, LocalDateTime start, LocalDateTime end);
List<EmployeeAttendance> findByEmployee_EmpIdAndDateBetween(Long empId, LocalDateTime startDate, LocalDateTime endDate);

}
