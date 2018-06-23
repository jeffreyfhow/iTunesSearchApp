package com.jeffreyfhow.itunessearchapp.utility;

public class DataUtility {

    /**
     * Assumption that String is of format: "YYYY-MM-DDThh:mm:ssZ"
     */
    public static int getYearFromString(String dateString){
        return Integer.parseInt(dateString.substring(0,4));
    }
}
