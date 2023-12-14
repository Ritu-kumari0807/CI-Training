package com.practo.entity;

import com.practo.dictionary.EmployeeAttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_attendance")
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ema_id")
    private Long id;

    @Column(name = "ema_employeeId")
    private Long employeeId;

    @Column(name = "ema_date")
    private LocalDateTime date;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ema_attendance_status")
    private EmployeeAttendanceStatus attendanceStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
