package com.galexandrov.zoo.services.implementation;


import com.galexandrov.zoo.dtos.BiletCreateServiceModel;
import com.galexandrov.zoo.dtos.GuideCreateServiceModel;
import com.galexandrov.zoo.models.*;
import com.galexandrov.zoo.repositories.UsersRepository;
import com.galexandrov.zoo.services.GuideService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuideServiceImpl implements GuideService {

    private final UsersRepository usersRepository;

    @Override
    public void reserveTicketForUser(String email, GuideCreateServiceModel serviceModel) throws Exception {

        User user = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> new Exception("User not found"));

        if (user.getGuide() != null) {
            throw new Exception("User already has a guide");
        }

        Guide guide = new Guide();
        guide.setName(serviceModel.getEkskurzovod().toString());
        guide.setUser(user);
        user.setGuide(guide);
        if(guide.getName().equals("Боковски")){
            guide.setGender(Gender.MALE);
            guide.setLanguage(Language.ENGLISH);
            guide.setSpecialized(Specialized.БОЗАЙНИЦИ);
        }else {
            guide.setGender(Gender.FEMALE);
            guide.setLanguage(Language.FRENCH);
            guide.setSpecialized(Specialized.ВЛЕЧУГИ);
        }
        usersRepository.save(user);
    }
}
