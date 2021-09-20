package com.galexandrov.zoo.services.implementation;

import com.galexandrov.zoo.dtos.BiletCreateServiceModel;
import com.galexandrov.zoo.models.Bilet;
import com.galexandrov.zoo.models.Ticket;
import com.galexandrov.zoo.models.User;
import com.galexandrov.zoo.repositories.UsersRepository;
import com.galexandrov.zoo.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final UsersRepository usersRepository;

    @Override
    public void reserveTicketForUser(String email, BiletCreateServiceModel serviceModel) throws Exception {
        User user = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getTicket() != null) {
            throw new Exception("User already has a ticket");
        }

        int max = 100;
        int min = 1;
// create instance of Random class
        Random randomNum = new Random();
        int showMe = min + randomNum.nextInt(max);

        Ticket ticket = new Ticket();
        ticket.setBilet(serviceModel.getBilet());
        ticket.setUser(user);
        user.setTicket(ticket);
        if(ticket.getBilet().equals(Bilet.малък)){
            ticket.setCena("4");
            ticket.setMqsto(showMe);
            ticket.setBiletaImaLiGid(true);
            ticket.setValidnostDni(5);
        }else if(ticket.getBilet().equals(Bilet.среден)){
            ticket.setCena("7");
            ticket.setMqsto(showMe);
            ticket.setBiletaImaLiGid(true);
            ticket.setValidnostDni(10);
        }else if(ticket.getBilet().equals(Bilet.голям)){
            ticket.setCena("10");
            ticket.setMqsto(showMe);
            ticket.setBiletaImaLiGid(true);
            ticket.setValidnostDni(20);
        }
        usersRepository.save(user);
    }
}
