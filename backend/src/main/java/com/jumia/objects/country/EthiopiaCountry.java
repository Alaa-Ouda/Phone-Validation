package com.jumia.objects.country;

public class EthiopiaCountry extends Country{
    public EthiopiaCountry(){
        countryName = "Ethiopia";
        countryCode = "251";
        countryPhoneRegex = "\\(251\\)\\ ?[1-59]\\d{8}$";
    }
}
