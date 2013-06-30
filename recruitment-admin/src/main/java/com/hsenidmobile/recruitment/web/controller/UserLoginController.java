package com.hsenidmobile.recruitment.web.controller;

import com.hms.oauth.config.OAuthConfiguration;
import com.hms.oauth.config.OAuthKeyBox;
import com.hms.oauth.exception.OAuthException;
import com.hms.oauth.provider.GoogleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * <p>
 * controller for handling user user login, authentication and logout operations
 * </p>
 */
@Controller
@RequestMapping("/auth")
public class UserLoginController {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("googleOAuthConfig")
    private OAuthConfiguration oAuthConfiguration;

    /**
     * <p>
     * display the open id login page for the user
     * </p>
     * @param model as {@link ModelMap}
     * @return the logical view name of the open id login page as {@link String}
     * @throws OAuthException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(ModelMap model) throws OAuthException {
        ModelAndView modelAndView = new ModelAndView();
        logger.debug("Received request to show login page");
        GoogleProvider googleProvider = new GoogleProvider(oAuthConfiguration);
        String googleLoginUrl = googleProvider.getAuthorizationUrl();
        model.put("googleLoginUrl",googleLoginUrl);
        modelAndView.setViewName("open-id-login-page");
        return modelAndView;
    }

    /**
     * <p>
     * display the access denied page upon the un-authorized user access
     * </p>
     * @return the logical view name of the access denied page as {@link String}
     */
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public ModelAndView showAccessDeniedPage() {
        logger.debug("Received request to show denied page");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access_denied_page");
        return modelAndView;
    }

    /**
     *<p>
     * the purpose of this method is to authenticate the google users with spring security
     *</p>
     * @param request as {@link HttpServletRequest}
     * @param modelMap as {@link ModelMap}
     * @return logical view name for the home page or login page pon user authentication as {@link String}
     */
    @RequestMapping(value = "/google",method = RequestMethod.GET)
    public ModelAndView googleUserAuthentication(HttpServletRequest request,ModelMap modelMap) throws OAuthException {
        String defaultGooglePassword = "admin";
        ModelAndView modelAndView = new ModelAndView();
        GoogleProvider googleProvider = new GoogleProvider(oAuthConfiguration);
        String googleLoginUrl = googleProvider.getAuthorizationUrl();

        if(request!=null && request.getAttribute(OAuthKeyBox.OAUTH_STATUS).equals("success")){
            logger.info(" starting the spring security integration with google");
            String googleUsername = (String)request.getAttribute(OAuthKeyBox.GOOGLE_EMAIL);
            //sending for user authentication with spring security
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(googleUsername, defaultGooglePassword);
            UserDetails user = new User("username", "password", true, true, true, true,new ArrayList<GrantedAuthority>());
            token.setDetails(user);
            try {
                Authentication auth = authenticationManager.authenticate(token);
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.debug(" Login succeeded! for the user [{}]",username);
                request.getSession().setAttribute("username",googleUsername);
                logger.info(" current locale of user [{}] is [{}] ",username,request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
                modelAndView.setViewName("redirect:../welcome");
            } catch (BadCredentialsException e) {
                logger.debug(" exception occurred while authenticating the user and exception message [{}]",e.getMessage());
                modelAndView.setViewName("open-id-login-page");
                modelMap.put("error","Your google account does not associate with our cooperate google domain");
                modelMap.put("googleLoginUrl",googleLoginUrl);
            }
        }
        else{
            if(request!=null && request.getAttribute(OAuthKeyBox.OAUTH_STATUS).equals("error")){
                modelMap.put("error",request.getAttribute(OAuthKeyBox.OAUTH_MESSAGE));
                modelMap.put("googleLoginUrl",googleLoginUrl);
            }
            modelAndView.setViewName("open-id-login-page");
        }
        return modelAndView;
    }
}