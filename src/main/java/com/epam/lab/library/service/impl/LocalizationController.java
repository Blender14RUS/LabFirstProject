package com.epam.lab.library.service.impl;

public class LocalizationController {
    private static String lang="en_US";

    public static void setLang(String language){
        lang=language;
    }

    public static String getLang(){
        return lang;
    }
}
