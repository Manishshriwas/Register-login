package com.example.RegistrationLogin.EmployeeController;

import com.example.RegistrationLogin.Dto.EmployeeDto;
import com.example.RegistrationLogin.Dto.LoginDto;
import com.example.RegistrationLogin.Service.EmployeeService;
import com.example.RegistrationLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDto employeeDto){
        String id = employeeService.addEmployee(employeeDto);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDto){
        LoginResponse loginMessage = employeeService.loginEmployee(loginDto);
        return ResponseEntity.ok(loginMessage);
    }

    // Define the LoginMessage class for the response
    public static class LoginMessage {
        private String message;

        public LoginMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
