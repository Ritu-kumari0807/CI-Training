package com.practo.repository;

import com.practo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmpEmail(String email);
    boolean existsByEmpPhoneNumber(String phoneNumber);
    Employee findByEmpPhoneNumber(String phoneNumber);
    Employee findByEmpEmail(String email);
    Employee findByEmpFirstName(String firstName);
    Optional<Employee> findByEmpId(Long empId);
}
