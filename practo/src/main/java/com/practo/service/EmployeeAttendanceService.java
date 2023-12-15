package com.practo.service;

import com.practo.exception.DuplicateEntryException;
import com.practo.payload.EmployeeAttendanceDTO;
import org.springframework.data.domain.Page;

public interface EmployeeAttendanceService {
    EmployeeAttendanceDTO addEmployeeAttendance(EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException;

    public Page<EmployeeAttendanceDTO> getAllEmployeeAttendance(int pageNo, int pageSize);

    EmployeeAttendanceDTO getEmployeeAttendanceById(Long id);

    EmployeeAttendanceDTO updateEmployeeAttendance(Long id, EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException;

    void deleteEmployeeAttendance(Long id);
}
