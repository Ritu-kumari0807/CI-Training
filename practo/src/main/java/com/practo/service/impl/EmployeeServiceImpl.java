package com.practo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practo.dictionary.APIErrorCode;
import com.practo.dictionary.EmployeeAttendanceStatus;
import com.practo.entity.EmployeeAttendance;
import com.practo.entity.EmployeePayslip;
import com.practo.exception.BadRequestException;
import com.practo.exception.DuplicateEntryException;
import com.practo.exception.InspireException;
import com.practo.exception.ResourceNotFoundException;
import com.practo.entity.Employee;
import com.practo.mapper.EmployeeMapper;
import com.practo.payload.EmployeeDto;
import com.practo.repository.EmployeePayslipRepository;
import com.practo.repository.EmployeeRepository;
import com.practo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeePayslipRepository employeePayslipRepository;


    //taking model mapper for converting from dto to entity and vice versa
    private ModelMapper modelmapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelmapper) {

        this.employeeRepository = employeeRepository;
        this.modelmapper = modelmapper;
    }

    //overriding the addEmployee method
    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws DuplicateEntryException, BadRequestException, InspireException {

        Employee employee = new Employee();
        EmployeeDto.checkForNull(employeeDto);
        employee = objectMapper.convertValue(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelmapper.map(savedEmployee, EmployeeDto.class);
    }

    //overriding the getEmployeeById method
    @Override
    public EmployeeDto getEmployeeById(Long id) throws InspireException {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new InspireException(APIErrorCode.RESOURCE_NOT_FOUND, "Employee not found with id " + id));
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

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(employee -> modelmapper.map(employee, EmployeeDto.class));
    }

    // overriding the deleteById method
    @Override
    public void deleteById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeRepository.deleteById(id);
    }

    //overriding the updateEmployeeById method
    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) throws DuplicateEntryException, InspireException {
     /*   Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new InspireException(APIErrorCode.RESOURCE_NOT_FOUND, "Employee not found with id " + id));


//         Update employee details
        if (employeeDto.getFirstName() != null) {
            checkForDuplicateFirstName(employeeDto.getFirstName(), id);
            employee.setEmpFirstName(employeeDto.getFirstName());
        } else {
            throw new BadRequestException("firstname field must be provided for update");
        }

        if (employeeDto.getEmail() != null) {
            checkForDuplicateEmail(employeeDto.getEmail(), id);
            employee.setEmpEmail(employeeDto.getEmail());
        } else {
            throw new BadRequestException("email field must be provided for update");
        }

        if (employeeDto.getPhoneNumber() != null) {
            checkForDuplicatePhoneNumber(employeeDto.getPhoneNumber(), id);
            employee.setEmpPhoneNumber(employeeDto.getPhoneNumber());
        } else {
            throw new BadRequestException("PhoneNumber field must be provided for update");
        }

        if (employeeDto.getLastName() != null) {
            employee.setEmpLastName(employeeDto.getLastName());
        } else {
            throw new BadRequestException("lastname field must be provided for update");
        }

        if (employeeDto.getSalary() != 0) {
            employee.setEmpSalary(employeeDto.getSalary());
        } else {
            throw new BadRequestException("salary fields must be provided for update");
        }

        if (employeeDto.getDepartment() != null) {
            employee.setEmpDepartment(employeeDto.getDepartment());
        } else {
            throw new BadRequestException("department fields must be provided for update");
        }
        if (employeeDto.getAddress() != null) {
            employee.setEmpAddress((employeeDto.getAddress()));
        } else {
            throw new BadRequestException("Address fields must be provided for update");
        }
        // Save the updated employee
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);*/

        return null;
    }

    private void checkForDuplicateEmail(String email, Long id) throws DuplicateEntryException {
        //check email is already present or not
        Employee existingEmployee = employeeRepository.findByEmpEmail(email);
        if (existingEmployee != null && existingEmployee.getEmpId() != id) {
            throw new DuplicateEntryException("Email " + email + " is already associated with another employee");
        }
    }

    private void checkForDuplicatePhoneNumber(String phoneNumber, Long id) throws DuplicateEntryException {
        //check mobileNumber is already present or not
        Employee existingEmployee = employeeRepository.findByEmpPhoneNumber(phoneNumber);
        if (existingEmployee != null && existingEmployee.getEmpId() != id) {
            throw new DuplicateEntryException("Phone number " + phoneNumber + " is already associated with another employee");
        }
    }

    private void checkForDuplicateFirstName(String firstName, Long id) throws DuplicateEntryException {
        Employee existingEmployee = employeeRepository.findByEmpFirstName(firstName);
        if (existingEmployee != null && existingEmployee.getEmpId() != id) {
            throw new DuplicateEntryException("First name " + firstName + " is already associated with another employee");
        }
    }

    private void checkDuplicateEmail(String email) throws DuplicateEntryException {

        //check mobileNumber is already present or not
        if (employeeRepository.existsByEmpEmail(email)) {
            throw new DuplicateEntryException("Email is already present");
        }
    }

    private void checkDuplicatePhoneNumber(String phoneNumber) throws DuplicateEntryException {

        //check mobileNumber is already present or not
        if (employeeRepository.existsByEmpPhoneNumber(phoneNumber)) {
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
//    @Override
//    @Async
//    public void calculateAndSaveMonthlySalary() {
//        // Logic to calculate monthly salary based on attendance status
//        List<Employee> employees = employeeRepository.findAll();
//
//        for (Employee employee : employees) {
//            double monthlySalary = calculateMonthlySalaryForEmployee(employee);
//            saveMonthlySalary(employee, monthlySalary);
//        }
//    }
//    private double calculateMonthlySalaryForEmployee(Employee employee) {
//        double dailySalary = employee.getEmpSalary() / 30; // Assuming a 30-day month
//        int presentDays = 0;
//        List<EmployeeAttendance> attendanceRecords = employee.getEmployeeAttendances();
//        for (EmployeeAttendance attendance : attendanceRecords) {
//            if (attendance.getEmaAttendanceStatus() == EmployeeAttendanceStatus.PRESENT) {
//                presentDays++;
//            }
//        }
//        return dailySalary * presentDays;
//    }
//    private void saveMonthlySalary(Employee employee, double monthlySalary) {
//        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = currentDate.withDayOfMonth(1); // First day of the current month
//        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // Last day of the current month
//
//        EmployeePayslip payslip = new EmployeePayslip();
//        payslip.setEmployee(employee);
//        payslip.setEpsStartDate(startDate);
//        payslip.setEpsEndDate(endDate);
//        payslip.setEpsSalary(monthlySalary);
//        employeePayslipRepository.save(payslip);
//    }
}


