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


    public static Date getTwitterDate(String date, String timeZone) {
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        sf.setTimeZone(TimeZone.getTimeZone(timeZone));
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
        System.out.println("GMT Timestamp from Tweet: " + date);

        Pair<Double,Double> coords = Pair.with(-28.019981, 153.428073);

        //TODO: add timeout for coordinates outside timezone.

        getLocalTime(date, coords);

    }


    private Date getLocalTime(String date, Pair<Double,Double> coords){
        int offset;

        // retrieve timezone ID from tweet location
        String zone = TimezoneMapper.latLngToTimezoneString(coords.getValue0(),coords.getValue1());

        // get offset of timezone
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setTimeZone(TimeZone.getTimeZone(zone));

        // get date object with updated timezone
        Date d = getTwitterDate(date,zone);

        String localTime = sf.format(d.getTime());
        System.out.println("Tweet from timezone: " + sf.getTimeZone().getDisplayName());
        System.out.println("Adjusted Date/Time: " + localTime);

        return d;
    }
}
