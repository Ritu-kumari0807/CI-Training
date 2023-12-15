package com.practo.service.impl;

import com.practo.exception.BadRequestException;
import com.practo.exception.DuplicateEntryException;
import com.practo.exception.ResourceNotFoundException;
import com.practo.entity.Employee;
import com.practo.mapper.EmployeeMapper;
import com.practo.payload.EmployeeDto;
import com.practo.repository.EmployeeRepository;
import com.practo.service.EmployeeService;
import jdk.internal.org.objectweb.asm.commons.Remapper;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //taking model mapper for converting from dto to entity and vice versa
    private ModelMapper modelmapper;

    public EmployeeServiceImpl(ModelMapper modelmapper) {
        this.modelmapper = modelmapper;
    }

    //overriding the addEmployee method
    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws DuplicateEntryException, BadRequestException {

        Employee employee = new Employee();

        // Update employee details
        if (employeeDto.getFirstName() != null) {
            employee.setFirstName(employeeDto.getFirstName());
        } else {
            throw new BadRequestException("firstname field must be provided for update");
        }

        if (employeeDto.getEmail() != null) {
            employee.setEmail(employeeDto.getEmail());
        } else {
            throw new BadRequestException("email field must be provided for update");
        }

        if (employeeDto.getPhoneNumber() != null) {
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
        } else {
            throw new BadRequestException("PhoneNumber field must be provided for update");
        }

        if (employeeDto.getLastName() != null) {
            employee.setLastName(employeeDto.getLastName());
        } else {
            throw new BadRequestException("lastname field must be provided for update");
        }

        if (employeeDto.getSalary() != 0) {
            employee.setSalary(employeeDto.getSalary());
        } else {
            throw new BadRequestException("salary fields must be provided for update");
        }

        if (employeeDto.getDepartment() != null) {
            employee.setDepartment(employeeDto.getDepartment());
        } else {
            throw new BadRequestException("department fields must be provided for update");
        }

        //save an employee into DB
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    //overriding the getEmployeeById method
    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not Found with id " + id)
        );
        EmployeeDto dto = EmployeeMapper.mapToEmployeeDto(employee);
        return dto;
    }

    //overriding the getAllEmployee method
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> emp = employeeRepository.findAll();
        List<EmployeeDto> employeeDto = emp.stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
        return employeeDto;
    }

    //overriding the getAllEmployee method and implementing pagination
    @Override
    public Page<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(employee -> modelmapper.map(employee, EmployeeDto.class));
    }

    // overriding the deleteById method
    @Override
    public void deleteById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeRepository.deleteById(id);
    }

    //overriding the updateEmployeeById method
    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) throws DuplicateEntryException {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with id " + id));

        // Update employee details
        if (employeeDto.getFirstName() != null) {
            checkForDuplicateFirstName(employeeDto.getFirstName(), id);
            employee.setFirstName(employeeDto.getFirstName());
        } else {
            throw new BadRequestException("firstname field must be provided for update");
        }

        if (employeeDto.getEmail() != null) {
            checkForDuplicateEmail(employeeDto.getEmail(), id);
            employee.setEmail(employeeDto.getEmail());
        } else {
            throw new BadRequestException("email field must be provided for update");
        }

        if (employeeDto.getPhoneNumber() != null) {
            checkForDuplicatePhoneNumber(employeeDto.getPhoneNumber(), id);
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
        } else {
            throw new BadRequestException("PhoneNumber field must be provided for update");
        }

        if (employeeDto.getLastName() != null) {
            employee.setLastName(employeeDto.getLastName());
        } else {
            throw new BadRequestException("lastname field must be provided for update");
        }

        if (employeeDto.getSalary() != 0) {
            employee.setSalary(employeeDto.getSalary());
        } else {
            throw new BadRequestException("salary fields must be provided for update");
        }

        if (employeeDto.getDepartment() != null) {
            employee.setDepartment(employeeDto.getDepartment());
        } else {
            throw new BadRequestException("department fields must be provided for update");
        }
        // Save the updated employee
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
    
    private void checkForDuplicateEmail(String email, Long id) throws DuplicateEntryException {
        //check email is already present or not
        Employee existingEmployee = employeeRepository.findByEmail(email);
        if (existingEmployee != null && existingEmployee.getId() != id) {
            throw new DuplicateEntryException("Email " + email + " is already associated with another employee");
        }

    }

    private void checkForDuplicatePhoneNumber(String phoneNumber, Long id) throws DuplicateEntryException {
        //check mobileNumber is already present or not
        Employee existingEmployee = employeeRepository.findByPhoneNumber(phoneNumber);
        if (existingEmployee != null && existingEmployee.getId() != id) {
            throw new DuplicateEntryException("Phone number " + phoneNumber + " is already associated with another employee");
        }
    }

    private void checkForDuplicateFirstName(String firstName, Long id) throws DuplicateEntryException {
        Employee existingEmployee = employeeRepository.findByFirstName(firstName);
        if (existingEmployee != null && existingEmployee.getId() != id) {
            throw new DuplicateEntryException("First name " + firstName + " is already associated with another employee");
        }
    }

    private void checkDuplicateEmail(String email) throws DuplicateEntryException {
        //check mobileNumber is already present or not
        if (employeeRepository.existsByEmail(email)) {
            //Handle the case where the employee with the given mobileNumber is already exist
            throw new DuplicateEntryException("Email is already present");
        }
    }

    private void checkDuplicatePhoneNumber(String phoneNumber) throws DuplicateEntryException {
        //check mobileNumber is already present or not
        if (employeeRepository.existsByPhoneNumber(phoneNumber)) {
            //Handle the case where the employee with the given mobileNumber is already exist
            throw new DuplicateEntryException("Phone number is already present");
        }
    }


    //  converting from DTO to Entity using Model Mapper
    Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = modelmapper.map(employeeDto, Employee.class);
        return employee;
    }

    //  converting from Entity to DTO using Model Mapper
    EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = modelmapper.map(employee, EmployeeDto.class);
        return dto;

    }
}


