package com.galexandrov.zoo.cotrollers;

import com.galexandrov.zoo.dtos.EmployeeLoginDto;
import com.galexandrov.zoo.dtos.LoginEmployeeServiceModel;
import com.galexandrov.zoo.dtos.LoginUserServiceModel;
import com.galexandrov.zoo.dtos.UserRegisterDto;
import com.galexandrov.zoo.models.Employee;
import com.galexandrov.zoo.models.User;
import com.galexandrov.zoo.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {
    private ModelMapper modelMapper = new ModelMapper();
    private AuthService authService;

    @Autowired
    public EmployeeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login-employee")
    public String getInfo() {
        return ("employee/login.html");
    }

    @GetMapping("/logged-employee")
    public String getLogged(){
        return ("employee/logged-employee.html");
    }
    @GetMapping("/graphic")
    public String getSchedule(){
        return ("employee/graphic.html");
    }

    @PostMapping("/login-employee")
    public ModelAndView login(@ModelAttribute EmployeeLoginDto employeeLoginDto, ModelAndView modelAndView, HttpSession session) {
        modelMapper.map(employeeLoginDto, Employee.class);
        try {
            LoginEmployeeServiceModel loginServiceModel = authService.employeeLogin(employeeLoginDto);
            session.setAttribute("user", loginServiceModel);
            modelAndView.setViewName("redirect:/logged-employee");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("/errors/main-page-exception.html");
            return modelAndView;
        }
    }
}
