package com.jumia.helpers;

import com.jumia.objects.CustomerPhone;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerPhoneCleanliness {
    CustomerPhone customerPhone;

    public void removeCountryCodeParentheses(){
        customerPhone.setCountryCode(customerPhone.getCountryCode().replace("(", "").replace(")", ""));
    }

    public void splitPhoneNumberWithSpace(){
        String[] phoneNumberParts = customerPhone.getCustomerPhoneNumber().split(" ");
        customerPhone.setCountryCode(phoneNumberParts[0]);
        customerPhone.setPhoneNumberWithoutCode(phoneNumberParts[1]);
    }
}
