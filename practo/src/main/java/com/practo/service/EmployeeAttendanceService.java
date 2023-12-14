package com.practo.service;

import com.practo.Exception.DuplicateEntryException;
import com.practo.payload.EmployeeAttendanceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeAttendanceService {
    EmployeeAttendanceDTO addEmployeeAttendance(EmployeeAttendanceDTO attendanceDTO)throws DuplicateEntryException;

    Page<EmployeeAttendanceDTO> getAllEmployeeAttendances(Pageable pageable);

    EmployeeAttendanceDTO getEmployeeAttendanceById(Long id);


    EmployeeAttendanceDTO updateEmployeeAttendance(Long id, EmployeeAttendanceDTO attendanceDTO)throws DuplicateEntryException;

    void deleteEmployeeAttendance(Long id);
}
