package com.hsenidmobile.recruitment.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    /**
     * <p>
     *     display the home page of the web application
     *     this method will only support for HTTP GET requests
     * </p>
     * @return "welcome" logical name encapsulated in {@link ModelAndView}
     */
    @RequestMapping(value = {"/home","welcome","/"},method = RequestMethod.GET)
    public ModelAndView displayWelcomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        return modelAndView;
    }
}
