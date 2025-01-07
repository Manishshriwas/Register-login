package com.example.RegistrationLogin.Service;

import com.example.RegistrationLogin.Dto.EmployeeDto;
import com.example.RegistrationLogin.Dto.LoginDto;
import com.example.RegistrationLogin.response.LoginResponse;

public interface EmployeeService {

    String addEmployee(EmployeeDto employeeDto);

    LoginResponse loginEmployee(LoginDto loginDto);
}
