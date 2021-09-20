package com.galexandrov.zoo.cotrollers;

import com.galexandrov.zoo.dtos.LoginUserServiceModel;
import com.galexandrov.zoo.dtos.UserRegisterDto;
import com.galexandrov.zoo.models.User;
import com.galexandrov.zoo.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class Home {

    private ModelMapper modelMapper = new ModelMapper();
    private AuthService authService;

    @Autowired
    public Home(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/")
    public String getHome() {
        return ("home/home-page.html");
    }

    @GetMapping("/register-login")
    public String getRegister() {
        return ("user_register/register.html");
    }

    @GetMapping("/home")
    public String succefullyLogged(){
        return ("successfully-login/logged-page.html");
    }
    @GetMapping("/animals")
    public String getInfoAnimals(){
        return ("animals/jivotni.html");
    }
    @GetMapping("/map")
    public String getMap() {
        return ("karta/map.html");
    }

    @GetMapping("/profile")
    public String getProfile() {
        return ("user_register/profile.html");
    }
    @GetMapping("/animals-unlogged")
    public String getAnimalsUnlogged(){
        return ("animals/jivotni-unlogged.html");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterDto userRegisterDto, ModelAndView modelAndView) {
        User user = authService.register(userRegisterDto);
        modelMapper.map(user, UserRegisterDto.class);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserRegisterDto userRegisterDto, ModelAndView modelAndView, HttpSession session) {
        modelMapper.map(userRegisterDto, User.class);
        try {
            LoginUserServiceModel loginServiceModel = authService.login(userRegisterDto);
            session.setAttribute("user", loginServiceModel);
            modelAndView.setViewName("redirect:/home");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("/errors/main-page-exception.html");
            return modelAndView;
        }
    }
    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session, ModelAndView modelAndView) {
        session.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}