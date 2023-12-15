package com.practo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @Id
    private Long id;

    @NotNull(message = "First name should not be null")
    @NotBlank
//    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "last name should not be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")//
    private String lastName;

    @Email(message = "Invalid email format")
    @NotNull(message = "Email name should not be null")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @NotNull(message = "Department cannot be null")
    @Size(max = 50, message = "Department must be less than 50 characters")
    private String department;

    @NotNull(message = "Address cannot be null")
    @Size(max = 10, message = "Address must be less than 100 characters")
    private String address;

    @NotNull(message = "phone number cannot be null")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

//    @NotNull(message = "salary cannot be null")
//    @Positive(message = "Salary must be a positive number")
    private double salary;

}

