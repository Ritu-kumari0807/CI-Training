package com.practo.service;

import com.practo.exception.BadRequestException;
import com.practo.exception.DuplicateEntryException;
import com.practo.payload.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

//    unimplemented methods

    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws DuplicateEntryException, BadRequestException;

    public EmployeeDto getEmployeeById(Long id);

    public List<EmployeeDto> getAllEmployees();

    public Page<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir);

    public void deleteById(long id);

    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) throws DuplicateEntryException;

}