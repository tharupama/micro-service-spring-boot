package com.springTharupama.emplayee_service.controller;

import com.springTharupama.emplayee_service.dto.ApiResponceDto;
import com.springTharupama.emplayee_service.dto.EmployeeDto;
import com.springTharupama.emplayee_service.entity.Employee;
import com.springTharupama.emplayee_service.servide.EmployeeService;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
  @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee) {
    EmployeeDto saveEmployee = employeeService.saveEmployee(employee);
    return new ResponseEntity<EmployeeDto>(saveEmployee,HttpStatus.CREATED);
  }

  @GetMapping("{id}")
    public ResponseEntity<ApiResponceDto> getEmployeeById(@PathVariable Long id) {
    ApiResponceDto apiResponceDto = employeeService.getEmployeeById(id);

      return new ResponseEntity<ApiResponceDto>(apiResponceDto,HttpStatus.OK);
  }



}
