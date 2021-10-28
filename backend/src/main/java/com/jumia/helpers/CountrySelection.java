package com.jumia.helpers;

import com.jumia.objects.country.*;

public class CountrySelection {

    public static Country getCountry(String key){
        Country country = null;
        switch (key){
            case "237":
            case "cameroon":
                country = new CameroonCountry();
                break;
            case "251":
            case "ethiopia":
                country = new EthiopiaCountry();
                break;
            case "212":
            case "morocco":
                country = new MoroccoCountry();
                break;
            case "258":
            case "mozambique":
                country = new MozambiqueCountry();
                break;
            case "256":
            case "uganda":
                country = new UgandaCountry();
                break;
        }
        return country;
    }
}
