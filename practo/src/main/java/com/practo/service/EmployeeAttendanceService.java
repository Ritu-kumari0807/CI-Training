package com.practo.service;

import com.practo.entity.EmployeeAttendance;
import com.practo.exception.DuplicateEntryException;
import com.practo.payload.EmployeeAttendanceDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeAttendanceService {
    EmployeeAttendanceDTO addEmployeeAttendance(EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException;

//    List<EmployeeAttendance> getAttendancesByEmployeeIdAndDateRange(Long emaEmployeeId, LocalDateTime start, LocalDateTime end);

    public Page<EmployeeAttendanceDTO> getAllEmployeeAttendance(int pageNo, int pageSize);

    EmployeeAttendanceDTO getEmployeeAttendanceById(Long id);

    EmployeeAttendanceDTO updateEmployeeAttendance(Long id, EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException;

    void deleteEmployeeAttendance(Long id);
}
