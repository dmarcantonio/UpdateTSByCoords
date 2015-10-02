package com.TimeZoneMap.test;

/**
 * Created by dave on 02-Oct-15.
 */

import com.TimeZoneMap.mapper.TimezoneMapper;
import org.junit.Test;
import org.javatuples.Pair;

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


    public static Date getTwitterDate(String date) {
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        sf.setTimeZone(TimeZone.getTimeZone("GMT"));
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
        String date = "Wed Aug 27 13:08:45 +0000 2008";

        tweetDate = getTwitterDate(date);

        if (tweetDate==null) return;

        Pair<Double,Double> coords = Pair.with(59.438442, 24.753463);

        System.out.println("Result: " + getLocalTime(tweetDate, coords));





    }


    private String getLocalTime(Date tweetTs, Pair<Double,Double> coords){
        int offset;

        long tweetMS = tweetTs.getTime();

        // retrieve timezone ID from tweet location
        String zone = TimezoneMapper.latLngToTimezoneString(coords.getValue0(),coords.getValue1());

        // get offset of timezone
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setTimeZone(TimeZone.getTimeZone(zone));
        /*offset = tz.getOffset(new Date().getTime()); // / 1000 / 60;
        System.out.println("offset ID of timezone:" + tz.getDisplayName());
*/
        String localTime = sf.format(tweetMS);
        System.out.println("Tweet from timezone: " + sf.getTimeZone().getDisplayName());
        System.out.println("Adjusted Date/Time: " + localTime);



        return localTime;
    }
}


/*
        final Date currentTime = new Date();
        final SimpleDateFormat sdf =
                    new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");

            // Give it to me in GMT time.
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println("GMT time: " + sdf.format(currentTime));*/