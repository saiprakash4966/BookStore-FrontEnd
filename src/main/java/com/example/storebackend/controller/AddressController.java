package com.example.storebackend.controller;


import com.example.storebackend.dto.AddressDto;
import com.example.storebackend.dto.ResponseDto;
import com.example.storebackend.model.Address;
import com.example.storebackend.service.IAddressDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressDetailsService addService;

    @PostMapping("/userDetails")
    public ResponseEntity<ResponseDto> customerAddressDetails(@RequestBody AddressDto addressDto) {
        Address userDetails = addService.customerDetails(addressDto);
        System.out.println("User: "+userDetails);
        ResponseDto response = new ResponseDto("Customer details added Successfully", userDetails.getAddressId());
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @GetMapping("/userAddress/{userId}")
    public ResponseEntity<ResponseDto> getAddressIdWiseDetails(@PathVariable("userId") int userId){
        Address addressList = addService.getData(userId);
        System.out.println("User List:" +addressList);
        ResponseDto response = new ResponseDto("User details of given Id ", addressList);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);


    }
}
