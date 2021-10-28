package com.jumia.controllers;

import com.jumia.objects.CountryCustomerPhone;
import com.jumia.services.CountryCustomerPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@RequestMapping("/phonevalidation")
public class CountryCustomerPhoneController {
    @Autowired
    CountryCustomerPhoneService countryCustomerPhoneService;

    @GetMapping(value = "/phones")
    public List<CountryCustomerPhone> listAllPhones (){
        return countryCustomerPhoneService.listAll();
    }


    @PostMapping(value = "/phones")
    public List<CountryCustomerPhone> listAllPhonesWithFilters (@RequestBody CountryCustomerPhone filteredCountryCustomerPhone){
        return countryCustomerPhoneService.listAllWithFilters(filteredCountryCustomerPhone);
    }
}
