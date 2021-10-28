package com.jumia.services;

import com.jumia.helpers.*;
import com.jumia.objects.*;
import com.jumia.objects.country.*;
import com.jumia.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryCustomerPhoneService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CountryCustomerPhone> listAll(){
        List<String> customersPhones = customerRepository.findPhones();
        List<CountryCustomerPhone> countryCustomerPhones = getCountryCustomerPhones(customersPhones, null);
        return countryCustomerPhones;
    }

    public List<CountryCustomerPhone> listAllWithFilters(CountryCustomerPhone filteredCountryCustomerPhone){
        List<String> customersPhones = null;

        if(Utils.isNotNullNotEmptyNotBlank(filteredCountryCustomerPhone.getCountryName())) {
            Country selectedCountry = CountrySelection.getCountry(filteredCountryCustomerPhone.getCountryName().toLowerCase());
            if(selectedCountry != null)
                customersPhones = customerRepository.findPhoneByCountryCode(selectedCountry.getCountryCode());
        }

        if(filteredCountryCustomerPhone.getState() != null) {
            if(customersPhones == null)
                customersPhones = customerRepository.findPhones();
        }

        List<CountryCustomerPhone> countryCustomerPhones = getCountryCustomerPhones(customersPhones, filteredCountryCustomerPhone.getState());
        return countryCustomerPhones;
    }

    public List<CountryCustomerPhone> getCountryCustomerPhones(List<String> customersPhones, Boolean state) {
        List<CountryCustomerPhone> countryCustomerPhones = new ArrayList<>();
        customersPhones.forEach(c -> {
            CustomerPhone customerPhone = new CustomerPhone();
            customerPhone.setCustomerPhoneNumber(c);

            CustomerPhoneCleanliness customerPhoneCleanliness = new CustomerPhoneCleanliness(customerPhone);
            customerPhoneCleanliness.splitPhoneNumberWithSpace();
            customerPhoneCleanliness.removeCountryCodeParentheses();

            Country country = CountrySelection.getCountry(customerPhone.getCountryCode());

            CountryCustomerPhone countryCustomerPhone = new CountryCustomerPhone();
            CountryCustomerPhoneMapper countryCustomerPhoneMapper = new CountryCustomerPhoneMapper(countryCustomerPhone);
            countryCustomerPhoneMapper.mapCustomerPhone(country, customerPhone);

            if (state == null || (state != null && countryCustomerPhone.getState().equals(state)))
                countryCustomerPhones.add(countryCustomerPhone);
        });
        return countryCustomerPhones;
    }
}
