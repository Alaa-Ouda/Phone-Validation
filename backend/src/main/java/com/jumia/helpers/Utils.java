package com.jumia.helpers;

import org.apache.commons.lang3.StringUtils;

public class Utils {
    public static Boolean isNotNullNotEmptyNotBlank (String testStr){
        if(testStr != null && StringUtils.isNotEmpty(testStr) && StringUtils.isNoneBlank(testStr))
            return true;
        return false;
    }
}
