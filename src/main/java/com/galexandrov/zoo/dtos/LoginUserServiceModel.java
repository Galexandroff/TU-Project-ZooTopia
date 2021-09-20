package com.galexandrov.zoo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserServiceModel {
    private String email;
    private String bilet;
    private String username;
    private String phone;
    private String ekskurzovod;
    private String srok;

    public LoginUserServiceModel(String email, String bilet, String username, String phone,String ekskurzovod,String srok) {

        this.email = email;
        this.bilet = bilet;
        this.username = username;
        this.phone = phone;
        this.ekskurzovod=ekskurzovod;
        this.srok=srok;
    }

}
