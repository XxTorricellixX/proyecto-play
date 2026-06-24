package com.about_software.proyecto_play.persistence.mapper;

import com.about_software.proyecto_play.domain.Genre;

public class StateMapper {

    public static Boolean stringToBoolean(String s){

        if(s==null)  return null;

        return switch (s){
            case "D" -> true;
            case "N" -> false;
            default -> null;
        };
    }

    public static String booleanToString(Boolean b) {
        if (b == null) return null;
        if (b) return "N";
        else return "D";
    }

}
