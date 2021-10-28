package com.jumia.helpers;

public class CustomerPhoneValidation {

    public static Boolean validatePhoneNumberWithRegex(String phoneNumber, String countryPhoneRegex){
        if(phoneNumber != null)
            return phoneNumber.matches(countryPhoneRegex);
        return false;
    }

}
