package com.practo.controller;

import com.practo.entity.Employee;
import com.practo.exception.BadRequestException;
import com.practo.exception.DuplicateEntryException;
import com.practo.payload.EmployeeDto;
import com.practo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //http://localhost:8080/api/employee/add
    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) throws DuplicateEntryException, BadRequestException {

        EmployeeDto savedEmployee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/employee/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    // http://localhost:8080/api/employee/all
    @GetMapping("/all")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return employeeDtos;
    }

    //http://localhost:8080/api/employee/page?pageNo=0&pageSize=5
    @GetMapping("/page")
    public Page<EmployeeDto> getAllEmployees(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        Page<EmployeeDto> postDtos = employeeService.getAllEmployees(pageNo, pageSize, sortBy, sortDir);
        return postDtos;

    }

    //http://localhost:8080/api/employee/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id, @RequestBody EmployeeDto employeeDto) throws DuplicateEntryException {
        EmployeeDto updatedEmployee = employeeService.updateEmployeeById(id, employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    //http://localhost:8080/api/employee/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>("Employee  Deleted Successfully", HttpStatus.OK);

    }
}


