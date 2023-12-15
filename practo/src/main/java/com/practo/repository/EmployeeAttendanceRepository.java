package com.practo.repository;

import com.practo.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    boolean existsByEmployeeId(Long employeeId);
}
