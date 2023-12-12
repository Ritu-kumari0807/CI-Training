package com.practo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto
{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String address;
    private String phoneNumber;
    private double salary;


}

