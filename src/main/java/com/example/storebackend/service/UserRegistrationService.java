package com.example.storebackend.service;

import com.example.storebackend.dto.LoginDto;
import com.example.storebackend.dto.UserRegistrationDto;
import com.example.storebackend.exception.UserRegistrationException;
import com.example.storebackend.model.UserRegistrationData;
import com.example.storebackend.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserRegistrationService implements IUserRegistrationService {


    @Autowired
    UserRegistrationRepository userRepo;



    @Override
    public List<UserRegistrationData> getUserDeatils() {
        return userRepo.findAll();
    }


    @Override
    public UserRegistrationData userRegistration(UserRegistrationDto userDTO) {
        UserRegistrationData userData = new UserRegistrationData();
        userData.createUser(userDTO);
        userRepo.save(userData);
        return userData;
    }



    @Override
    public UserRegistrationData getUserById(int userId) {
        System.out.println("Userid:" +userId);
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserRegistrationException("User  with id " + userId + " does not exist in database..!"));
    }

    @Override
    public UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDto userDTO) {
        UserRegistrationData userData = this.getUserById(userId);
        userData.updateUser(userDTO);
        return userRepo.save(userData);
    }

    @Override
    public void deleteUser(int userId) {
        UserRegistrationData userData = this.getUserById(userId);
        userRepo.delete(userData);
    }

    @Override
    public List<UserRegistrationData> getAllUsersData() {
        List<UserRegistrationData> usersList = userRepo.findAll();
        return usersList;
    }

    @Override
    public String verifyUser() {
        return null;
    }


    @Override
    public Optional<UserRegistrationData> UserLogin(LoginDto logindto) {
        Optional<UserRegistrationData> userLogin = userRepo.findByEmailIdAndPassword(logindto.emailId, logindto.password);

        if (userLogin.isPresent()) {
            log.info("LOGIN SUCCESSFUL");
            return userLogin;
        } else {
            log.error("User not Found Exception:");

            return null;
        }
    }

    @Override
    public String deleteAll() {
        userRepo.deleteAll();
        return "Successfully deleted all the users";
    }
}
