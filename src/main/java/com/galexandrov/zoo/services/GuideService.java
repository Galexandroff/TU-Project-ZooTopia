package com.galexandrov.zoo.services;

import com.galexandrov.zoo.dtos.GuideCreateServiceModel;

public interface GuideService {


    void reserveTicketForUser(String username, GuideCreateServiceModel serviceModel) throws Exception;
}
