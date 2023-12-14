package com.practo.service;

import com.practo.Exception.DuplicateEntryException;
import com.practo.payload.EmployeeAttendanceDTO;
import com.practo.payload.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeAttendanceService {
    EmployeeAttendanceDTO addEmployeeAttendance(EmployeeAttendanceDTO attendanceDTO)throws DuplicateEntryException;

    public Page<EmployeeAttendanceDTO> getAllEmployeeAttendance(int pageNo, int pageSize);

    EmployeeAttendanceDTO getEmployeeAttendanceById(Long id);


    EmployeeAttendanceDTO updateEmployeeAttendance(Long id, EmployeeAttendanceDTO attendanceDTO)throws DuplicateEntryException;

    void deleteEmployeeAttendance(Long id);
}