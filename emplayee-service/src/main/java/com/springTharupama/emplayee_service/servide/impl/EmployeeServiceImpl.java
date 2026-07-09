package com.springTharupama.emplayee_service.servide.impl;


import com.springTharupama.emplayee_service.dto.ApiResponceDto;
import com.springTharupama.emplayee_service.dto.DepartmentDto;
import com.springTharupama.emplayee_service.dto.EmployeeDto;
import com.springTharupama.emplayee_service.entity.Employee;
import com.springTharupama.emplayee_service.repository.EmployeeRepository;
import com.springTharupama.emplayee_service.servide.ApiClient;
import com.springTharupama.emplayee_service.servide.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private ModelMapper modelMapper;//because of all args constructor constructor injection happen

    private EmployeeRepository employeeRepository;

    private ApiClient apiClient;

//    private RestTemplate restTemplate;

   // private WebClient webClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee =  modelMapper.map(employeeDto, Employee.class);
        employeeRepository.save(employee);
        Employee savedEmployee =  employeeRepository.save(employee);
        EmployeeDto employeeDtoSaved = modelMapper.map(savedEmployee, EmployeeDto.class);
        return employeeDtoSaved;
    }
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")//to retry before circuit send responsee as properties
    @Override
    public ApiResponceDto getEmployeeById(Long id) {
        logger.info("getEmployeeById method inside");
        Employee employee = employeeRepository.findById(id).get();
//communication with rest template
//        ResponseEntity<DepartmentDto> responseEntity =  restTemplate.getForEntity("http://localhost:8080/api/v1/department/"
//                + employee.getDepartmentCode(), DepartmentDto.class);
//         DepartmentDto departmentDto =  responseEntity.getBody();

        //communication with web client
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/v1/department/"+employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDto foundEmployeeDto = modelMapper.map(employee, EmployeeDto.class);

        ApiResponceDto apiResponceDto = new ApiResponceDto(foundEmployeeDto,departmentDto);
        return apiResponceDto;
    }

    public ApiResponceDto getDefaultDepartment(Long id,Exception exception){
        logger.info("getDefaultDepartment method inside");
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto(Long.parseLong("404"),"Default Department","development department","D001");
        EmployeeDto foundEmployeeDto = modelMapper.map(employee, EmployeeDto.class);

        ApiResponceDto apiResponceDto = new ApiResponceDto(foundEmployeeDto,departmentDto);
        return apiResponceDto;
    }

}
