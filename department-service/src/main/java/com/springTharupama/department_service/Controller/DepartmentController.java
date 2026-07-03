package com.springTharupama.department_service.Controller;

import com.springTharupama.department_service.dto.DepartmentDto;
import com.springTharupama.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentDto1 = departmentService.saveDepartment(departmentDto);
        //return ResponseEntity.ok().body(departmentDto1);
        return new ResponseEntity<DepartmentDto> (departmentDto1, HttpStatus.CREATED);
    }

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto>getDepartmentByCode(@PathVariable(value = "department-code") String code) {
        DepartmentDto departmentDto = departmentService. getDepartmentByCode(code);
        return new ResponseEntity<DepartmentDto>(departmentDto,HttpStatus.OK);
    }

}
