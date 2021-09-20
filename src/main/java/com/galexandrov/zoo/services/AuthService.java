package com.galexandrov.zoo.services;

import com.galexandrov.zoo.dtos.EmployeeLoginDto;
import com.galexandrov.zoo.dtos.LoginEmployeeServiceModel;
import com.galexandrov.zoo.dtos.LoginUserServiceModel;
import com.galexandrov.zoo.dtos.UserRegisterDto;
import com.galexandrov.zoo.models.User;

public interface AuthService {

    User register(UserRegisterDto userRegisterDto);

    String hashPassword(String str);

    LoginUserServiceModel login(UserRegisterDto userRegisterDto);

    LoginEmployeeServiceModel employeeLogin(EmployeeLoginDto employeeLoginDto);
}
