package com.jumia.objects;

import lombok.Data;

@Data
public class CustomerPhone {
    String customerPhoneNumber;
    String countryCode;
    String phoneNumberWithoutCode;
}
