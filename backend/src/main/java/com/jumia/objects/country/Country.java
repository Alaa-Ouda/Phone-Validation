package com.jumia.objects.country;

import lombok.Data;

@Data
public abstract class Country {
    String countryName;
    String countryCode;
    String countryPhoneRegex;

    public String getCountryCodeWithPlus(){
        return "+" + countryCode;
    }
}
