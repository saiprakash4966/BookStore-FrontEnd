package com.example.storebackend.service;

import com.example.storebackend.dto.LoginDto;
import com.example.storebackend.dto.UserRegistrationDto;
import com.example.storebackend.model.UserRegistrationData;

import java.util.List;
import java.util.Optional;

public interface IUserRegistrationService {
    List<UserRegistrationData> getUserDeatils();

    UserRegistrationData userRegistration(UserRegistrationDto userDTO);

    UserRegistrationData getUserById(int userId);

    UserRegistrationData updateUserRegistrationData(int userId, UserRegistrationDto userDTO);

    void deleteUser(int userId);

    List<UserRegistrationData> getAllUsersData();

    String verifyUser();

    Optional<UserRegistrationData> UserLogin(LoginDto logindto);

//    String forgotPassword(ForgotPasswordDto forgotpass);

//    UserRegistrationData resetPassword(ResetPassword resetPassword, String token);

    String deleteAll();
}

