package com.example.storebackend.model;


import com.example.storebackend.dto.AddressDto;
import com.example.storebackend.dto.UserRegistrationDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "userregistration")
@Data
public class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name = "fullName")
    private String fullName;


    @Column(name = "emailId")
    private String emailId;
    @Column
    private String password;

    @Column
    private String mobileNumber;
    //    @Column(name = "registerDate")
//    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate registerDate;
//    @Column(name = "updatedDate")
//    @JsonFormat(pattern = "dd-MM-yyyy")

    private LocalDate updatedDate;

    //    @Column(name = "purchaseDate")
//    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date purchaseDate;


    public UserRegistrationData() {

    }

    public void createUser(UserRegistrationDto userDTO) {
        this.fullName = userDTO.fullName;
        this.mobileNumber= userDTO.mobileNumber;
        this.emailId = userDTO.emailId;
        this.password = userDTO.password;
        this.registerDate = LocalDate.now();
        this.purchaseDate = userDTO.purchaseDate;
    }

    public void updateUser(UserRegistrationDto userDTO) {
        this.fullName = userDTO.fullName;
        this.mobileNumber= userDTO.mobileNumber;
        this.emailId = userDTO.emailId;
        this.password = userDTO.password;
        this.updatedDate = LocalDate.now();
        this.purchaseDate = userDTO.purchaseDate;
    }

    public void updateAddress(AddressDto addressDto) {
//        this.fullName = addressDto.fullName;
//        this.mobileNumber = addressDto.mobileNumber;
//        this.pincode = addressDto.pincode;
//        this.address = addressDto.address;
//        this.locality = addressDto.locality;
//        this.city = addressDto.city;
//        this.landmark = addressDto.landmark;
//        this.addressType = addressDto.addressType;
    }
}
