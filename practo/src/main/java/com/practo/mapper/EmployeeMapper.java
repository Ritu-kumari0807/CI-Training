package com.practo.mapper;

import com.practo.entity.Employee;
import com.practo.payload.EmployeeDto;

public class EmployeeMapper {

    // Convert User Entity into UserDto
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto userDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getSalary()

        );
        return userDto;
    }

    // Convert UserDto into User JPA Entity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartment(),
                employeeDto.getSalary(),
                employeeDto.getAddress(),
                employeeDto.getPhoneNumber()

        );
        return employee;
    }
}
