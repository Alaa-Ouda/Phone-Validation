package com.jumia.helpers;

import com.jumia.objects.CountryCustomerPhone;
import com.jumia.objects.CustomerPhone;
import com.jumia.objects.country.Country;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CountryCustomerPhoneMapper {
    CountryCustomerPhone countryCustomerPhone;

    public void mapCustomerPhone(Country country, CustomerPhone customerPhone){
        countryCustomerPhone.setCountryName(country.getCountryName());
        countryCustomerPhone.setCountryCode(country.getCountryCodeWithPlus());
        countryCustomerPhone.setPhoneNumber(customerPhone.getCustomerPhoneNumber());
        countryCustomerPhone.setState(CustomerPhoneValidation.validatePhoneNumberWithRegex(customerPhone.getCustomerPhoneNumber(), country.getCountryPhoneRegex()));
    }
}
