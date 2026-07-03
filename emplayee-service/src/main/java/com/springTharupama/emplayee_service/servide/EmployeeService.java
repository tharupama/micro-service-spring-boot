package com.springTharupama.emplayee_service.servide;

import com.springTharupama.emplayee_service.dto.ApiResponceDto;
import com.springTharupama.emplayee_service.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employee);

    ApiResponceDto getEmployeeById(Long id);
}
