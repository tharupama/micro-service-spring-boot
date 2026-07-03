package com.springTharupama.emplayee_service.repository;

import com.springTharupama.emplayee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> id(Long id);


}
