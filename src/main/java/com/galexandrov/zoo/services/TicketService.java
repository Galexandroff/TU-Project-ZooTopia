package com.galexandrov.zoo.services;

import com.galexandrov.zoo.dtos.BiletCreateServiceModel;
import com.galexandrov.zoo.models.Ticket;

public interface TicketService {


    void reserveTicketForUser(String username, BiletCreateServiceModel serviceModel) throws Exception;
}
