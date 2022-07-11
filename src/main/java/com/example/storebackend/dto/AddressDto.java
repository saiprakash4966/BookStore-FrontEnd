package com.example.storebackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class AddressDto
{
    public int userId;
    public String fullName;
    public String mobileNumber;
    public String pincode;
    public String address;
    public String locality;
    public String city;
    public String landmark;
    public String addressType;
}
