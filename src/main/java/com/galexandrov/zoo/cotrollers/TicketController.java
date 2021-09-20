package com.galexandrov.zoo.cotrollers;

import com.galexandrov.zoo.dtos.BiletCreateModel;
import com.galexandrov.zoo.dtos.BiletCreateServiceModel;
import com.galexandrov.zoo.dtos.LoginUserServiceModel;
import com.galexandrov.zoo.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TicketController extends BaseController {
    private TicketService ticketService;

    private ModelMapper modelMapper = new ModelMapper();

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public String getInfo() {
        return ("ticket/bilet.html");
    }

    @PostMapping("/reserve")
    public String createTicket(@ModelAttribute BiletCreateModel biletCreateModel, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "/";
        }

        String username = getUsername(session);
        String userUsername = getUserUsernam(session);
        String phone=getPhone(session);
        String guide=getGuide(session);
        String srok=getTicketSrok(session);

        BiletCreateServiceModel serviceModel = modelMapper.map(biletCreateModel, BiletCreateServiceModel.class);
        try {
            ticketService.reserveTicketForUser(username, serviceModel);
            LoginUserServiceModel loginUserServiceModel = new LoginUserServiceModel(username,biletCreateModel.getBilet(),userUsername,phone,guide,srok);
            session.setAttribute("user", loginUserServiceModel);

            return "redirect:/guide-choose";
        } catch (Exception ex) {
            return "/errors/main-page-exception.html";
        }
    }
}
