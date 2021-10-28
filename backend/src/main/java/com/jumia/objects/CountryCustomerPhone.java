package com.jumia.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryCustomerPhone {
    String countryName;
    String countryCode;
    Boolean state;
    String phoneNumber;
}
