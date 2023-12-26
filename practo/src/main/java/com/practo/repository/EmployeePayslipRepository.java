package com.practo.repository;

import com.practo.entity.EmployeePayslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePayslipRepository extends JpaRepository<EmployeePayslip,Long> {
    List<EmployeePayslip> findByEmployeeId(Long employeeId);
}
