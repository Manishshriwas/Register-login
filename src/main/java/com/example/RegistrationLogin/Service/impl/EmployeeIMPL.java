package com.example.RegistrationLogin.Service.impl;

import com.example.RegistrationLogin.Dto.EmployeeDto;
import com.example.RegistrationLogin.Dto.LoginDto;
import com.example.RegistrationLogin.Entity.Employee;
import com.example.RegistrationLogin.Repo.EmployeeRepo;
import com.example.RegistrationLogin.Service.EmployeeService;
import com.example.RegistrationLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getEmployeeid(),
                employeeDto.getEmployeename(),
                employeeDto.getEmail(),
                this.passwordEncoder.encode(employeeDto.getPassword())
        );
        employeeRepo.save(employee);
        return employee.getEmployeename();
    }

    @Override
    public LoginResponse loginEmployee(LoginDto loginDto) {
        // Find employee by email
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepo.findByEmail(loginDto.getEmail()));

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            // Compare passwords
            if (passwordEncoder.matches(loginDto.getPassword(), employee.getPassword())) {
                return new LoginResponse("Login Success", true);
            } else {
                return new LoginResponse("Password does not match", false);
            }
        } else {
            return new LoginResponse("Email not found", false);
        }
    }
}
