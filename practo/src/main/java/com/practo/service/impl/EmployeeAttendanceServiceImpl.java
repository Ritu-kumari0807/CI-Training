package com.practo.service.impl;

import com.practo.exception.AttendanceNotFoundException;
import com.practo.exception.DuplicateEntryException;
import com.practo.exception.ResourceNotFoundException;
import com.practo.entity.EmployeeAttendance;
import com.practo.payload.EmployeeAttendanceDTO;
import com.practo.repository.EmployeeAttendanceRepository;
import com.practo.service.EmployeeAttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {
    private final ModelMapper modelMapper;
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    @Autowired
    public EmployeeAttendanceServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //method to add attendance
    @Override
    public EmployeeAttendanceDTO addEmployeeAttendance(EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException {

        //  check for null fields
        // Check for duplicate getEmployeeId
        checkForDuplicateEmployeeId(attendanceDTO.getEmployeeId());
        EmployeeAttendance attendance = convertToEntity(attendanceDTO);
        attendance.setCreatedAt(LocalDateTime.now());
        attendance.setUpdatedAt(LocalDateTime.now());
        EmployeeAttendance savedAttendance = employeeAttendanceRepository.save(attendance);
        return convertToDTO(savedAttendance);
    }

    //method to converting from entity to dto
    private EmployeeAttendanceDTO convertToDTO(EmployeeAttendance savedAttendance) {
        return modelMapper.map(savedAttendance, EmployeeAttendanceDTO.class);
    }

    //method to converting from dto to entity
    private EmployeeAttendance convertToEntity(EmployeeAttendanceDTO attendanceDTO) {
        return modelMapper.map(attendanceDTO, EmployeeAttendance.class);
    }

    // method to check employeeId is already present or not
    private void checkForDuplicateEmployeeId(long employeeId) throws DuplicateEntryException {

        //check employeeId is already present or not
        if (employeeAttendanceRepository.existsByEmployeeId(employeeId)) {

            //Handle the case where the employee with the given employeeId is already exist

            throw new DuplicateEntryException("employeeId is already present");
        }
    }

    //method to get All employeeAttandance
    @Override
    public Page<EmployeeAttendanceDTO> getAllEmployeeAttendance(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return employeeAttendanceRepository.findAll(pageable).map(this::convertToDTO);
    }

    //method to employeeAttandance getById
    @Override
    public EmployeeAttendanceDTO getEmployeeAttendanceById(Long id) {
        EmployeeAttendance attendance = employeeAttendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException("Employee Attendance not found with id: " + id));
        return convertToDTO(attendance);
    }

    //method to update employeeAttandance
    @Override
    public EmployeeAttendanceDTO updateEmployeeAttendance(Long id, EmployeeAttendanceDTO attendanceDTO) throws DuplicateEntryException {
        EmployeeAttendance existingEmployee = employeeAttendanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not present")
        );
        existingEmployee.setDate(attendanceDTO.getDate());
        existingEmployee.setAttendanceStatus(attendanceDTO.getAttendanceStatus());
        existingEmployee.setUpdatedAt(LocalDateTime.now());
        EmployeeAttendance updatedAttendance = employeeAttendanceRepository.save(existingEmployee);
        return convertToDTO(updatedAttendance);
    }

    //method to Delete employeeAttandance
    @Override
    public void deleteEmployeeAttendance(Long id) {
        EmployeeAttendance deleteAttendance = employeeAttendanceRepository.findById(id).orElseThrow(
                () -> new AttendanceNotFoundException("EmployeeAttendance not found with id " + id));

        employeeAttendanceRepository.deleteById(id);
    }
}
