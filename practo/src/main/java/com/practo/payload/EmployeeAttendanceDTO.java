package com.practo.payload;

import com.practo.dictionary.EmployeeAttendanceStatus;
import lombok.Data;

import java.time.LocalDateTime;


import com.practo.dictionary.EmployeeAttendanceStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

    @Data
    public class EmployeeAttendanceDTO
    {
        private Long id;

        @NotNull(message = "Employee ID cannot be null")
        @Positive(message = "Employee ID must be a positive number")
        private Long employeeId;

        @NotNull(message = "Date cannot be null")
        private LocalDateTime date;

        @NotNull(message = "Attendance status cannot be null")
        private EmployeeAttendanceStatus attendanceStatus;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}

