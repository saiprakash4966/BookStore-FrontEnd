package com.example.storebackend.model;

import com.example.storebackend.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int addressId;

    @Column(name = "Name")
    private String fullName;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "address")
    private String address;

    @Column(name = "locality")
    private String locality;

    @Column(name = "city")
    private String city;

    @Column(name = "landMark")
    private String landmark;

    @Column(name = "type")
    private String addressType;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserRegistrationData user;

    public Address(UserRegistrationData user, AddressDto addressDto) {
        this.user=user;
        AddressData(addressDto);

    }

    public void AddressData(AddressDto addressDto) {

        this.fullName = addressDto.fullName;
        this.mobileNumber = addressDto.mobileNumber;
        this.pincode = addressDto.pincode;
        this.address = addressDto.address;
        this.locality = addressDto.locality;
        this.city = addressDto.city;
        this.landmark = addressDto.landmark;
        this.addressType = addressDto.addressType;
    }

    public void updateAddress(AddressDto addressDto) {
        AddressData(addressDto);
    }

    public Address() {

    }
}
