package com.practo.payload;

import com.practo.dictionary.APIErrorCode;
import com.practo.entity.Employee;
import com.practo.entity.EmployeeProfile;
import com.practo.exception.InspireException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long empId;

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String empFirstName;

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String empLastName;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String empEmail;

    @Size(max = 50, message = "Department must be less than 50 characters")
    private String empDepartment;

    @Size(max = 10, message = "Address must be less than 100 characters")
    private String empAddress;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String empPhoneNumber;

    private double empSalary;

    private Set<EmployeeProfile> employeeProfile;

    public static void checkForNull(EmployeeDto employeeDto) throws InspireException {

        if (employeeDto.getEmpFirstName() == null)
            throw new InspireException(APIErrorCode.INVALID_REQUEST, "Employee name should not be null for creation");

        if (employeeDto.getEmpEmail() == null) {
            throw new InspireException(APIErrorCode.BAD_REQUEST, "Email should be provided for creation");
        }

        if (employeeDto.getEmpPhoneNumber() == null) {
            throw new InspireException(APIErrorCode.BAD_REQUEST, "PhoneNumber should be provided for creation");
        }

        if (employeeDto.getEmpLastName()== null) {
            throw new InspireException(APIErrorCode.INVALID_REQUEST, "Employee Lastname should not be null for creation");
        }

        if (employeeDto.getEmpSalary() <= 0) {
            throw new InspireException(APIErrorCode.INVALID_REQUEST, "Employee salary should not be null for creation");
        }

        if (employeeDto.getEmpDepartment() == null) {
            throw new InspireException(APIErrorCode.INVALID_REQUEST, "Employee Department should not be null for creation");
        }
        if(employeeDto.getEmpAddress() ==null) {
            throw new InspireException(APIErrorCode.INVALID_REQUEST, "Employee Address should not be null for creation");
        }
}
}

