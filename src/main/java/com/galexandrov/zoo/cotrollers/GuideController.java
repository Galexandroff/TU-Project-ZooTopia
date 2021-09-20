package com.galexandrov.zoo.cotrollers;

import com.galexandrov.zoo.dtos.*;
import com.galexandrov.zoo.services.GuideService;
import com.galexandrov.zoo.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class GuideController extends BaseController{
    private GuideService guideService;

    private ModelMapper modelMapper = new ModelMapper();

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping("/guide")
    public String getInfoGuide() {
        return ("guide/guide-info.html");
    }

    @GetMapping("/guide-choose")
    public String chooseGuide(){
        return("guide/guide-choose.html");
    }
    @GetMapping("/end")
    public String end(){
        return("guide/end.html");
    }
    @PostMapping("/guide-reserve")
    public String createGuide(@ModelAttribute GuideCreateModel guideCreateModel, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);
        String userUsername = getUserUsernam(session);
        String phone=getPhone(session);
        String ticket=getTicket(session);
        String srok=getTicketSrok(session);


        GuideCreateServiceModel serviceModel = modelMapper.map(guideCreateModel, GuideCreateServiceModel.class);
        try {
            guideService.reserveTicketForUser(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username, guideCreateModel.getEkskurzovod(),userUsername,phone,ticket,srok);
            session.setAttribute("user", loginUserServiceModel);

            return "redirect:/end";
        } catch (Exception ex) {
            return "/errors/main-page-exception.html";
        }
    }
}
