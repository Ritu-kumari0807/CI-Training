package com.practo.controller;

import com.practo.exception.DuplicateEntryException;
import com.practo.payload.EmployeeAttendanceDTO;
import com.practo.service.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employeeattendance")
public class EmployeeAttendanceController {

    private final EmployeeAttendanceService attendanceService;

    @Autowired
    public EmployeeAttendanceController(EmployeeAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //http://localhost:8080/employeeattendance/addAttendance
    @PostMapping("/addAttendance")
    public ResponseEntity<EmployeeAttendanceDTO> addEmployeeAttendance(@Valid @RequestBody EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException {
        EmployeeAttendanceDTO createdAttendance = attendanceService.addEmployeeAttendance(attendanceDTO);
        return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
    }

    // http://localhost:8080/employeeattendance/all?pageNo=0&pageSize=5&sortBy=id&sortDir=asc
    @GetMapping("/all")
    public ResponseEntity<Page<EmployeeAttendanceDTO>> getAllEmployeeAttendances(@RequestParam(defaultValue = "0") int pageNo,
                                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<EmployeeAttendanceDTO> attendances = attendanceService.getAllEmployeeAttendance(pageNo, pageSize);
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    // http://localhost:8080/employeeattendance/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAttendanceDTO> getEmployeeAttendanceById(@PathVariable Long id) {
        EmployeeAttendanceDTO attendance = attendanceService.getEmployeeAttendanceById(id);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    // http://localhost:8080/employeeattendance/updateAttendance/{id}
    @PutMapping("/updateAttendance/{id}")
    public ResponseEntity<EmployeeAttendanceDTO> updateEmployeeAttendance(@PathVariable Long id, @RequestBody EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException {
        EmployeeAttendanceDTO updatedAttendance = attendanceService.updateEmployeeAttendance(id, attendanceDTO);
        return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
    }

    // http://localhost:8080/employeeattendance/deleteAttendance/{id}
    @DeleteMapping("/deleteAttendance/{id}")
    public ResponseEntity<String> deleteEmployeeAttendance(@PathVariable Long id) {
        attendanceService.deleteEmployeeAttendance(id);
        return new ResponseEntity<>("Employee  Deleted Successfully!!", HttpStatus.OK);
    }
}
