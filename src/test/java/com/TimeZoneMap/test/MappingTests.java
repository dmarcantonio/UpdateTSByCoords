package com.TimeZoneMap.test;

/**
 * Created by dave on 02-Oct-15.
 */

import com.TimeZoneMap.mapper.TimezoneMapper;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;


public class MappingTests {

    @Test
    public void test()
    {
        System.out.println("Sydney: " + TimezoneMapper.latLngToTimezoneString(-33, 151));
        System.out.println("Helsinki: " + TimezoneMapper.latLngToTimezoneString(60.173154, 24.940936));
        System.out.println("London: " + TimezoneMapper.latLngToTimezoneString(51.516007, -0.121644));
        System.out.println("Paris: " + TimezoneMapper.latLngToTimezoneString(48.856696, 2.352077));
        System.out.println("Talinn: " + TimezoneMapper.latLngToTimezoneString(59.438442, 24.753463));
        System.out.println("Jeddah: " + TimezoneMapper.latLngToTimezoneString(21.518043, 39.191228));
        System.out.println("Chattanooga: " + TimezoneMapper.latLngToTimezoneString(35.03217, -85.19392));
        System.out.println("Gold Coast: " + TimezoneMapper.latLngToTimezoneString(-28.019981, 153.428073));
        System.out.println("Oulu (Finland): " + TimezoneMapper.latLngToTimezoneString(65.012197, 25.471152));
    }

    private static final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    private static SimpleDateFormat sf = new SimpleDateFormat(TWITTER);

    public static Date getTwitterDate(String date) {

        sf.setLenient(true);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Test
    public void testFull(){
        Date tweetDate;

        tweetDate = getTwitterDate("Wed Aug 27 13:08:45 +0000 2008");

        if (tweetDate==null) return;

        double lat = -33;
        double lng = 151;

        Date result = getLocalTime(tweetDate, lat, lng);





    }


    private Date getLocalTime(Date tweetTs, double lat, double lng){
        int offset;

        long tweetMS = tweetTs.getTime();

        // retrieve timezone ID from tweet location
        String zone = TimezoneMapper.latLngToTimezoneString(lat, lng);

        // get offset of timezone
        TimeZone tz = TimeZone.getTimeZone(zone);
        offset = tz.getOffset(new Date().getTime()); // / 1000 / 60;

//        SimpleTimeZone stz = new SimpleTimeZone(offset, zone);

        System.out.println("Offset for " + zone + " is " + offset);
        System.out.println(new SimpleDateFormat("HH:mm").format(offset));

        // let's try applying the offset to the original date
        long localTime = tweetMS + offset;



        return null;
    }
}


/*
        final Date currentTime = new Date();
        final SimpleDateFormat sdf =
                    new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");

            // Give it to me in GMT time.
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println("GMT time: " + sdf.format(currentTime));*/