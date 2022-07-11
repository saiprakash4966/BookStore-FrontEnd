package com.example.storebackend.controller;

import com.example.storebackend.dto.LoginDto;
import com.example.storebackend.dto.ResponseDto;
import com.example.storebackend.dto.UserRegistrationDto;
import com.example.storebackend.exception.UserRegistrationException;
import com.example.storebackend.model.UserRegistrationData;
import com.example.storebackend.service.IUserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
@RequestMapping("/userregistrationservice")
@Slf4j
public class UserRegistrationController {
    @Autowired
    private IUserRegistrationService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> addUserRegistrationData(@RequestBody UserRegistrationDto userDTO) {
        UserRegistrationData userDetails = service.userRegistration(userDTO);
        log.debug("User Registration input details: " + userDTO.toString());
        ResponseDto response = new ResponseDto("Verification Mail Has Been Sent Successfully", userDetails.getUserId());
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }



    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDto> updateContactData(@PathVariable("userId") int userId,
                                                         @Valid @RequestBody UserRegistrationDto userDTO) {
        UserRegistrationData updateUser = service.updateUserRegistrationData(userId, userDTO);
        log.debug("User Registration Data After Update " + updateUser.toString());
        ResponseDto response = new ResponseDto("Updated contact data for" + userId, updateUser);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseDto> getContactData(@PathVariable("userId") int userId){

        UserRegistrationData users = service.getUserById(userId);

        if (users!=null) {
            ResponseDto responseDTO = new ResponseDto("User Fetched successfully", users);
            return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.OK);
        } else {
            throw new UserRegistrationException("No Data Found");
        }
    }



    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("userId") int userId) {
        service.deleteUser(userId);
        ResponseDto response = new ResponseDto("Delete call success for id ", "deleted id:" + userId);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }


    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseDto> readdata() throws UserRegistrationException {
        List<UserRegistrationData> users = null;
        users = service.getAllUsersData();
        if (users.size() > 0) {
            ResponseDto responseDTO = new ResponseDto("all user Fetched successfully", users);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            throw new UserRegistrationException("No Data Found");
        }
    }

    @PostMapping("/userlogin")
    public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto logindto) {
        Optional<UserRegistrationData> login = service.UserLogin(logindto);
        if (login != null) {
            ResponseDto dto = new ResponseDto("LOGIN SUCCESSFUL", login);
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        } else {
            ResponseDto dto = new ResponseDto("User login not successfully", login);
            return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
        }
    }
}
