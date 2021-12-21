package com.univpm.oop.WeatherPal.controller;

import com.univpm.oop.WeatherPal.model.Filters.DailyPeriod;
import com.univpm.oop.WeatherPal.model.Filters.HourlyPeriod;
import com.univpm.oop.WeatherPal.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class StatsController {


    @Autowired
    StatsService statsService;

    @RequestMapping(value = "/stats/period", method = RequestMethod.GET)
    public ResponseEntity<Object> getPeriod(@RequestParam(name = "city") String city) {
        ResponseEntity<Object> response;

        if(city.toLowerCase().equals("ancona")) {
            HourlyPeriod hourlyPeriod = (HourlyPeriod) statsService.getPeriod("\\src\\main\\resources\\static\\Every1h");
            DailyPeriod dailyPeriod = statsService.getPeriod("\\src\\main\\resources\\static\\Weekly");

            String period = "The hourly stats are avaiable " + hourlyPeriod.toString() + "\nThe daily stats are avaiable " +
                    dailyPeriod.toString();

            response = new ResponseEntity<Object>(period, HttpStatus.OK);
        } else {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(5);
            DailyPeriod dailyPeriod = new DailyPeriod(startDate, endDate);
            String period = "The historical stats are avaiable " + dailyPeriod.toString();

            response = new ResponseEntity<Object>(period, HttpStatus.OK);
        }
        return response;
    }
/*
    @RequestMapping(value = "/stats/hourly", method = RequestMethod.GET)
    public ResponseEntity<Object> getHourlyStats(@RequestParam(name = "city", defaultValue = "ancona") String city,
                                                  @RequestParam(name = "start date") String day1,
                                                 @RequestParam(name = "end date", required = false) String day2,
                                                 @RequestParam(name = "start time") String time1,
                                                 @RequestParam(name = "end time") String time2) {

        ResponseEntity<Object> response;

        if (day2.equals(""))
            day2 = day1;

        if(!Check.VerifyPattern(day1, day2, time1, time2)){
            response = new ResponseEntity<Object>("ERROR, WRONG PATTERN OF DATE OR TIME!", HttpStatus.BAD_REQUEST);
        } else {
            if (city.toLowerCase().equals("ancona")) {
                if (!Check.VerifyPeriod(day1, day2, time1, time2,)){
                    //lettura
                }
            } else {
                //leggi l'api
            }
        }
        HourlyPeriod hourlyPeriod = new HourlyPeriod();
        return new ResponseEntity<Object>(statsService.getHourlyStats(HourlyPeriod), HttpStatus.OK);
    }
    */

    /*
    @RequestMapping(value = "/stats/daily", method = RequestMethod.GET)
    public ResponseEntity<>   {
        return new
    }

    @RequestMapping(value = "/stats/weekly", method = RequestMethod.GET)
    public ResponseEntity<>   {
        return new
    }

    @RequestMapping(value = "/5dForecast", method = RequestMethod.GET)
    public ResponseEntity<>   {
        return new
    }
    */
}
