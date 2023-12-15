package com.practo.repository;

import com.practo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Employee findByPhoneNumber(String phoneNumber);

    Employee findByEmail(String email);

    Employee findByFirstName(String firstName);

}
