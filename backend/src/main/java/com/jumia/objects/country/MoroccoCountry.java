package com.jumia.objects.country;

public class MoroccoCountry extends Country{
    public MoroccoCountry(){
        countryName = "Morocoo";
        countryCode = "212";
        countryPhoneRegex = "\\(212\\)\\ ?[5-9]\\d{8}$";
    }
}
