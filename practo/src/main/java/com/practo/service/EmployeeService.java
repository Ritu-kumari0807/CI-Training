package com.practo.service;

import com.practo.entity.Employee;
import com.practo.payload.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

//    unimplemented methods

    public EmployeeDto addEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long id);


    public List<EmployeeDto> getAllEmployees();

    public Page<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir);

    public void deleteById(long id);

    public EmployeeDto updateEmployeeById(Long id,EmployeeDto employeeDto);
}