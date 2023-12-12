package com.practo.controller;

import com.practo.entity.Employee;
import com.practo.payload.EmployeeDto;
import com.practo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
       @Autowired
        private EmployeeService employeeService;

       //adding the employee
      //http://localhost:8080/api/employee/add
        @PostMapping("/add")
        public ResponseEntity <EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
        {

            EmployeeDto savedEmployee=employeeService.addEmployee(employeeDto);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

        }
    //getting employee by ID
   // http://localhost:8080/api/employee/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id)
    {
        EmployeeDto employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById,HttpStatus.OK);
    }
    //getting all employee
   //http://localhost:8080/api/employee/all
    @GetMapping("/all")
    public List<EmployeeDto> getAllEmployees()
    {
        List<EmployeeDto> employeeDtos=employeeService.getAllEmployees();
        return employeeDtos;
    }

     //getting employee from pagination
    // http://localhost:8080/api/employee/page?pageNo=0&pageSize=5&sortBy=id&sortDir=asc
    @GetMapping("/page")
    public Page<EmployeeDto> getAllEmployees(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
            @RequestParam(value ="sortBy" ,defaultValue="id",required=false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    )
    {
        Page<EmployeeDto> postDtos = employeeService.getAllEmployees(pageNo,pageSize, sortBy,sortDir);
        return postDtos;

    }
    //    updating employee
    //    http://localhost:8080/api/employee/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id,@RequestBody EmployeeDto employeeDto)
    {
       EmployeeDto updatedemployee= employeeService.updateEmployeeById(id,employeeDto);
       return new ResponseEntity<>(updatedemployee,HttpStatus.OK);
    }
    //    delete employeeBYId
    //    http://localhost:8080/api/employee/delete/{id}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id)
    {
        employeeService.deleteById(id);
        return new ResponseEntity<>("Employee  Deleted Successfully",HttpStatus.OK);

    }
}


