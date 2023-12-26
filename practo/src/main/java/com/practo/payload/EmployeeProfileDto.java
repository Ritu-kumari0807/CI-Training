package com.practo.payload;

import lombok.Data;

import javax.persistence.Column;
@Data
public class EmployeeProfileDto {
    private Long empProfileId;

    private Long empEmployeeId;

    private String empQualification;

    private String empDescription;
}
