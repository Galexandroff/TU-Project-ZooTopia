package com.galexandrov.zoo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginEmployeeServiceModel {

    private String code;

    public LoginEmployeeServiceModel( String code) {

        this.code = code;


    }

}
