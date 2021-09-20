package com.galexandrov.zoo.cotrollers;



import com.galexandrov.zoo.dtos.LoginUserServiceModel;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }
//take email for login
    protected String getUsername(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getEmail();
    }

    protected String getUserUsernam(HttpSession session) {
       return ((LoginUserServiceModel) session.getAttribute("user")).getUsername();
    }

   protected String getPhone(HttpSession session) {
       return ((LoginUserServiceModel) session.getAttribute("user")).getPhone();
   }
    protected String getGuide(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getEkskurzovod();
    }

    protected String getTicket(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getBilet();
    }
    protected String getTicketSrok(HttpSession session) {
        return ((LoginUserServiceModel) session.getAttribute("user")).getSrok();
    }
}