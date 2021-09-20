package com.galexandrov.zoo.services.implementation;

import com.galexandrov.zoo.dtos.EmployeeLoginDto;
import com.galexandrov.zoo.dtos.LoginEmployeeServiceModel;
import com.galexandrov.zoo.dtos.LoginUserServiceModel;
import com.galexandrov.zoo.dtos.UserRegisterDto;
import com.galexandrov.zoo.exceptions.ZooTopiaNotFoundException;
import com.galexandrov.zoo.models.User;
import com.galexandrov.zoo.repositories.EmployeeRepository;
import com.galexandrov.zoo.repositories.UsersRepository;
import com.galexandrov.zoo.services.AuthService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private UsersRepository usersRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public AuthServiceImpl(UsersRepository usersRepository, EmployeeRepository employeeRepository) {
        this.usersRepository = usersRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public User register(UserRegisterDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        Example<User> example = Example.of(user);
        Optional<User> optionalUser = usersRepository.findOne(example);
        if (!optionalUser.isEmpty()) {
            throw new ZooTopiaNotFoundException("Моля,опитайте отново.Потребител с този имейл вече съществува.");
        }

        User user1 = new User();
        user1.setUsername(userDto.getUsername());
        user.setUsername(user1.getUsername());
        Example<User> example1 = Example.of(user1);
        Optional<User> optionalUser1 = usersRepository.findOne(example1);
        if (optionalUser1.isPresent()) {
            throw new ZooTopiaNotFoundException("Моля,опитайте отново! Потребител с това име вече съществува.");
        }


        if (userDto.getUsername().isEmpty()) {
            throw new ZooTopiaNotFoundException("Моля,въведете валидно потребителско име. Полето не може да бъде празно !");
        }
        if (userDto.getEmail().isEmpty()) {
            throw new ZooTopiaNotFoundException("Моля,въведете валиден имейл. Полето не може да бъде празно !");
        }


        if (userDto.getPhone().isEmpty()) {
            throw new ZooTopiaNotFoundException("Моля,въведете валиден телефонен номер. Полето не може да бъде празно !");
        }
        if (userDto.getPassword().isEmpty()) {
            throw new ZooTopiaNotFoundException("Моля,въведете валидна парола. Полето не може да бъде празно !");
        }
        if (userDto.getConfirmPassword().isEmpty()) {
            throw new ZooTopiaNotFoundException("Моля,въведете валидна парола. Полето не може да бъде празно !");
        }

        user.setPassword(hashPassword(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        return usersRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(UserRegisterDto userRegisterDto) {
        String passwordHash = hashPassword(userRegisterDto.getPassword());
        return usersRepository
                .findByEmailAndPassword(userRegisterDto.getEmail(), passwordHash)
                .map(user -> {

                    String bilet = user.getTicket() == null
                            ? null
                            : user.getTicket().getBilet().toString();
                    String guide = user.getGuide() == null
                            ? null
                            : user.getGuide().getName().toString();

                    String srok = user.getTicket() == null
                            ? null
                            : user.getTicket().getValidnostDni().toString();

                    return new LoginUserServiceModel(userRegisterDto.getEmail(),bilet,user.getUsername(),user.getPhone(),guide,srok);
                })
                .orElseThrow(() -> new ZooTopiaNotFoundException("Invalid user"));
    }

    @Override
    public LoginEmployeeServiceModel employeeLogin(EmployeeLoginDto employeeLoginDto) {
        String passwordHash = hashPassword(employeeLoginDto.getPassword());
        return employeeRepository
                .findByCodeAndPassword(employeeLoginDto.getCode(), employeeLoginDto.getPassword())
                .map(user -> {



                    return new LoginEmployeeServiceModel(employeeLoginDto.getCode());
                })
                .orElseThrow(() -> new ZooTopiaNotFoundException("Invalid user"));
    }

    @Override
    public String hashPassword(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
