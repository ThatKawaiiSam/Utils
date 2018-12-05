package io.github.thatkawaiisam.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    public static String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(" MMMM, yyyy - hh:mma"));
    }

    public static String day(){
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("d"));
    }

    public static String getOrdinalSuffix( int value ) {
        int hunRem = value % 100;
        int tenRem = value % 10;

        if ( hunRem - tenRem == 10 ) {
            return "th";
        }
        switch ( tenRem ) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}
