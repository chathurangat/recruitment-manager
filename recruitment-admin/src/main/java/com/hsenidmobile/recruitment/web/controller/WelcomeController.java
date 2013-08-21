package com.hsenidmobile.recruitment.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    /**
     * <p>
     *     display the user home page of the recruitment web application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "welcome" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = {"/home","welcome"},method = RequestMethod.GET)
    public ModelAndView displayWelcomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/welcome");
        return modelAndView;
    }


    /**
     * <p>
     *     this is to check whether the spring security works as expected....
     *     this method is only accessible for the admin users
     * </p>
     * @return "admin_page" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView displayAdminPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/admin_page");
        return modelAndView;
    }

    /**
     * <p>
     *     this is to check whether the spring security works as expected....
     *     this method is only accessible for the admin users
     * </p>
     * @return "access_denied_page" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/denied",method = RequestMethod.GET)
    public ModelAndView displayAccessDeniedPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/access_denied_page");
        return modelAndView;
    }
}
