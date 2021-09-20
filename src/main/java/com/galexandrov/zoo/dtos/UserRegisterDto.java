package com.galexandrov.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    @NotEmpty(message = "This field cannot be empty. Please, provide your full name!")
    private String username;

    @NotEmpty(message = "This field cannot be empty. Please, provide your email!")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Please, enter real email")
    private String email;

    @NotEmpty(message = "This field cannot be empty. Please, provide your password!")
    private String password;

    @NotEmpty(message = "This field cannot be empty. Please, provide your password!")
    private String confirmPassword;


    @NotEmpty(message = "The field cannot be empty. Please, provide phone number!")
    @Pattern(regexp = "08[789]\\d{7}", message = "Please,enter real phone number!")
    private String phone;


}
