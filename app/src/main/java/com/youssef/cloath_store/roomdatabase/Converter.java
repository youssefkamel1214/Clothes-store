package com.youssef.cloath_store.roomdatabase;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class Converter {
    @TypeConverter
    public static Calendar fromTimestamp(Long value) {
        Calendar cal=Calendar.getInstance();
        if(value==null)
            return null;
        cal.setTimeInMillis(value);
        return cal ;
    }

    @TypeConverter
    public static Long CalnderToTimestamp(Calendar date) {
        return date == null ? null : date.getTimeInMillis();
    }
    @TypeConverter
    public static int ftoint(float num) {

        return  (int)(Math.round(num));
    }
    @TypeConverter
    public static float ftoint(int num) {
        return  (float) (Math.round(num));
    }
}
