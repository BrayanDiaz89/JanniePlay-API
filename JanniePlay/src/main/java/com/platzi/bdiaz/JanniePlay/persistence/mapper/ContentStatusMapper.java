package com.platzi.bdiaz.JanniePlay.persistence.mapper;

import org.mapstruct.Named;

public class ContentStatusMapper {

    @Named("stringToBoolean")
    public static Boolean stringToBoolean(String contentStatus){
        if(contentStatus == null) return false;
        return contentStatus.equalsIgnoreCase("D");
    }

    @Named("booleanToString")
    public static String booleanToString(Boolean isAvailable){
        return isAvailable == null ? "N" : isAvailable ? "D" : "N";
    }
}


