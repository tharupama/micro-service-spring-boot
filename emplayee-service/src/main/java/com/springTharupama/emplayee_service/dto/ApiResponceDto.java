package com.springTharupama.emplayee_service.dto;

import com.springTharupama.emplayee_service.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponceDto {
    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
}
