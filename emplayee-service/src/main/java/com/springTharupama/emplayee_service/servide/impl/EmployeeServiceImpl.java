package com.springTharupama.emplayee_service.servide.impl;

import com.springTharupama.emplayee_service.dto.ApiResponceDto;
import com.springTharupama.emplayee_service.dto.DepartmentDto;
import com.springTharupama.emplayee_service.dto.EmployeeDto;
import com.springTharupama.emplayee_service.entity.Employee;
import com.springTharupama.emplayee_service.repository.EmployeeRepository;
import com.springTharupama.emplayee_service.servide.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;//because of all args constructor constructor injection happen

    private EmployeeRepository employeeRepository;

    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee =  modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
        Employee savedEmployee =  employeeRepository.save(employee);
        EmployeeDto employeeDtoSaved = modelMapper.map(savedEmployee, EmployeeDto.class);
        return employeeDtoSaved;
    }

    @Override
    public ApiResponceDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();

        ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity("http://localhost:8080/api/v1/department/"
                + employee.getDepartmentCode(), DepartmentDto.class);

         DepartmentDto departmentDto =  responseEntity.getBody();

        EmployeeDto foundEmployeeDto = modelMapper.map(employee, EmployeeDto.class);

        ApiResponceDto apiResponceDto = new ApiResponceDto(foundEmployeeDto,departmentDto);
        return apiResponceDto;
    }


}
