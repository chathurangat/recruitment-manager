package com.hsenidmobile.recruitment.web.controller;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import com.hsenidmobile.recruitment.model.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/31/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

    @RequestMapping(value = "/calendar_client",method = RequestMethod.GET)
    public ModelAndView displayCalendarEventForm(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("calendar/calendar_test");

        return modelAndView;
    }

    @RequestMapping(value = "/calendar_register",method = RequestMethod.POST)
    public ModelAndView registerCalendarEvent(@Valid Calendar calendar,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession) throws IOException, ServiceException {

        URL feedUrl = new URL("http://www.google.com/calendar/feeds/tharanga%40hsenidmobile.com/private/full");
        CalendarService client = new CalendarService("com.hsenidmobile-recruitment-01");
        client.setUserCredentials("tharanga@hsenidmobile.com","T#@ranga20");
        CalendarEventEntry entry = new CalendarEventEntry();
        entry.setTitle(new PlainTextConstruct("This is a test"));

        DateTime startTime = DateTime.parseDateTime("2013-07-31T00:00:00-08:00");
        DateTime endTime = DateTime.parseDateTime("2013-08-01T17:00:00-08:00");
        When eventTimes = new When();
        eventTimes.setStartTime(startTime);
        eventTimes.setEndTime(endTime);
        entry.addTime(eventTimes);


      /*
        WebContent wc = new WebContent();

        wc.setTitle(calendar.getTitle());
        wc.setType("text/html");
        wc.setIcon("https://upload.wikimedia.org/wikipedia/commons/8/81/Wikimedia-logo.svg");
        wc.setUrl("http://www.google.com");
        wc.setWidth("800");
        wc.setHeight("600");


        entry.setWebContent(wc);  */



        client.insert(feedUrl, entry);
        System.out.println("Calendar Gadget is created");

        modelAndView.setViewName("calendar/success");
        return modelAndView;
    }

 }
