package com.practo.mapper;

import com.practo.entity.Employee;
import com.practo.payload.EmployeeDto;


public class EmployeeMapper {

    // Convert User Entity into UserDto
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeDto userDto = new EmployeeDto();
        userDto.setEmpId(employee.getEmpId());
        userDto.setEmpFirstName(employee.getEmpFirstName());
        userDto.setEmpLastName(employee.getEmpLastName());
        userDto.setEmpEmail(employee.getEmpEmail());
        userDto.setEmpDepartment(employee.getEmpDepartment());
        userDto.setEmpSalary(employee.getEmpSalary());
        userDto.setEmpAddress(employee.getEmpAddress());
        userDto.setEmpPhoneNumber(employee.getEmpPhoneNumber());
        userDto.setEmployeeProfile(employee.getEmployeeProfile());
        return userDto;
    }

    // Convert UserDto into User JPA Entity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmpId(employeeDto.getEmpId());
        employee.setEmpFirstName(employeeDto.getEmpFirstName());
        employee.setEmpLastName(employeeDto.getEmpLastName());
        employee.setEmpEmail(employeeDto.getEmpEmail());
        employee.setEmpDepartment(employeeDto.getEmpDepartment());
        employee.setEmpSalary(employeeDto.getEmpSalary());
        employee.setEmpAddress(employeeDto.getEmpAddress());
        employee.setEmpPhoneNumber(employeeDto.getEmpPhoneNumber());
        employee.setEmployeeProfile(employeeDto.getEmployeeProfile());
        return employee;
    }
}
