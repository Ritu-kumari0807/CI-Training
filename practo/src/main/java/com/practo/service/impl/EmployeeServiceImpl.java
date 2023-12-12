package com.practo.service.impl;

import com.practo.Exception.ResourceNotFoundException;
import com.practo.entity.Employee;
import com.practo.mapper.EmployeeMapper;
import com.practo.payload.EmployeeDto;
import com.practo.repository.EmployeeRepository;
import com.practo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

  //    taking model mapper for converting from dto to entity and vice versa
    private ModelMapper modelmapper;
    public EmployeeServiceImpl(ModelMapper modelmapper)
    {
        this.modelmapper=modelmapper;
    }

//    overriding the addEmployee method
    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto)
    {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        EmployeeDto dto=EmployeeMapper.mapToEmployeeDto(savedEmployee);
        return dto;

    }
    // overriding the getEmployeeById method
    @Override
    public EmployeeDto getEmployeeById(Long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not Found with id " + id)
        );
        EmployeeDto dto=EmployeeMapper.mapToEmployeeDto(employee);
        return dto;
    }
    //    overriding the getAllEmployee method
    @Override
    public List<EmployeeDto> getAllEmployees()
    {
        List<Employee> emp= employeeRepository.findAll();
        List<EmployeeDto> employeeDto = emp.stream()
                                       .map(e -> mapToDto(e))
                                       .collect(Collectors.toList());
        return employeeDto;
    }

//    overriding the getAllEmployee method and implementing pagination
     @Override
    public Page<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {

//        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
//                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
//        Page<Employee> employee = employeeRepository.findAll(pageable);
//        List<Employee> emp= employee.getContent();
//        List<EmployeeDto> employeeDto = emp.stream()
//                                       .map(e -> mapToDto(e))
//                                       .collect(Collectors.toList());
//        return employeeDto;
         Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                 Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

         Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
         Page<Employee> employeePage = employeeRepository.findAll(pageable);

         return employeePage.map(employee -> modelmapper.map(employee, EmployeeDto.class));
     }

    @Override
    public void deleteById(long id)
    {
        Employee employee= employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Employee not found with id "+id)
        );
        employeeRepository.deleteById(id);
    }


    //  converting from DTO to Entity using Model Mapper
        Employee mapToEntity(EmployeeDto employeeDto)
        {
            Employee employee = modelmapper.map(employeeDto, Employee.class);
            return employee;
        }
//  converting from Entity to DTO using Model Mapper
         EmployeeDto mapToDto(Employee employee)
         {

        EmployeeDto dto = modelmapper.map(employee, EmployeeDto.class);
        return dto;

          }
}

