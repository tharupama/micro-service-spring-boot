package com.springTharupama.emplayee_service.servide;

import com.springTharupama.emplayee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
@FeignClient(name="department-service")
public interface ApiClient {
    @GetMapping("api/v1/department/{department-code}")
    DepartmentDto getDepartment(@PathVariable(value = "department-code") String code);

}
