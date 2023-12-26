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
    @Column(name = "EMA_ID")
    private Long emaId;

    @Column(name = "EMA_EMPLOYEEID", insertable = false, updatable = false)
    private Long emaEmployeeId;

    @Column(name = "EMA_DATE")
    private LocalDateTime emaDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "EMA_ATTENDANCE_STATUS")
    private EmployeeAttendanceStatus emaAttendanceStatus;

    @Column(name = "EMA_CREATED_AT")
    private LocalDateTime emaCreatedAt;

    @Column(name = "EMA_UPDATED_AT")
    private LocalDateTime emaUpdatedAt;
}
