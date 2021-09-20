package com.galexandrov.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLoginDto {

    @NotEmpty(message = "This field cannot be empty. Please, provide your security code!")
    private String code;

    @NotEmpty(message = "This field cannot be empty. Please, provide your password!")
    private String password;
}
