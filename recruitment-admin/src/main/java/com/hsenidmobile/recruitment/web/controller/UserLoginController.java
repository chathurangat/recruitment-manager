/**
 *
 */
package com.hsenidmobile.recruitment.web.controller;

import com.hms.oauth.config.OAuthConfiguration;
import com.hms.oauth.config.OAuthKeyBox;
import com.hms.oauth.exception.OAuthException;
import com.hms.oauth.provider.FacebookProvider;
import com.hms.oauth.provider.GoogleProvider;
import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.service.ApplicantService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * <p>
 *     controller for handling user user login, authentication and logout operations
 * </p>
 */
@Controller
@RequestMapping("/auth")
public class UserLoginController {
    //todo remove sout statements
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired(required = false)
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;
    @Autowired
    private ApplicantService applicantService;
    private OAuthConfiguration oAuthConfiguration;
    private static final String APPLICATION_ID = "669970197155-7lhlm9iu7vgdv7dniif2kqs18ts5bt0h.apps.googleusercontent.com";
    private static final String APPLICATION_SECRET = "C05Nugr_LKdwrq8-K2bZd7tK";
    private static final String REDIRECT_URL = "http://localhost:8080/recruitment-admin/user/auth/google";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo#email";

    /**
     * <p>
     *     display the open id login page for the user
     * </p>
     * @param model as {@link ModelMap}
     * @return the logical view name of the open id login page as {@link String}
     * @throws OAuthException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(ModelMap model) throws OAuthException {
        ModelAndView modelAndView = new ModelAndView();
        logger.debug("Received request to show login page");
        this.initializeOAuthConfiguration();
        GoogleProvider googleProvider = new GoogleProvider(oAuthConfiguration);
        String googleLoginUrl = googleProvider.getAuthorizationUrl();
        model.put("googleLoginUrl",googleLoginUrl);
        modelAndView.setViewName("open_id_login_page");
        return modelAndView;
    }

    /**
     * <p>
     *     display the access denied page upon the un-authorized user access
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
     * @param request  as {@link HttpServletRequest}
     * @param modelMap as {@link ModelMap}
     * @return logical view name for the home page or login page pon user authentication as {@link String}
     */
    @RequestMapping(value = "/google",method = RequestMethod.GET)
    public ModelAndView googleUserAuthentication(HttpServletRequest request,ModelMap modelMap) throws OAuthException {

        String defaultGooglePassword = "admin";
//        String defaultFacebookPasswordInMd5Hashed = "21232f297a57a5a743894a0e4a801fc3";

        ModelAndView modelAndView = new ModelAndView();

        this.initializeOAuthConfiguration();
        GoogleProvider googleProvider = new GoogleProvider(oAuthConfiguration);
        String googleLoginUrl = googleProvider.getAuthorizationUrl();

        if(request!=null && request.getAttribute(OAuthKeyBox.OAUTH_STATUS).equals("success")){
            logger.info(" starting the spring security integration with google");
            //check whether the google user has accessed this website  previously
            String googleUsername = (String)request.getAttribute(OAuthKeyBox.GOOGLE_EMAIL);
            //sending for user authentication

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(googleUsername, defaultGooglePassword);
            UserDetails user = new User("username", "password", true, true, true, true,new ArrayList<GrantedAuthority>());
            token.setDetails(user);
            try {
                Authentication auth = authenticationManager.authenticate(token);
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.debug(" Login succeeded! for the user [{}]",username);
                System.out.println(" Login succeeded! for the user [{}]"+username);
                request.getSession().setAttribute("username",googleUsername);
                modelAndView.setViewName("welcome-redirect");
            } catch (BadCredentialsException e) {
                logger.info("error occurred "+e);
                logger.debug(" exception occurred while authenticating the user and exception message [{}]",e.getMessage());
                modelAndView.setViewName("open_id_login_page");
                modelMap.put("error","Your google account does not associate with our cooperate google domain");
                modelMap.put("googleLoginUrl",googleLoginUrl);
            }
        }
        else{
            if(request!=null && request.getAttribute(OAuthKeyBox.OAUTH_STATUS).equals("error")){
                modelMap.put("error",request.getAttribute(OAuthKeyBox.OAUTH_MESSAGE));
                modelMap.put("googleLoginUrl",googleLoginUrl);
            }
            modelAndView.setViewName("open_id_login_page");
        }
        return modelAndView;
    }


    /**
     * <p>
     *     initializing oauth configuration for the facebook network
     * </p>
     */
    private void initializeOAuthConfiguration(){
        //setting up oauth configurations
        oAuthConfiguration = new OAuthConfiguration();
        oAuthConfiguration.setApplicationId(APPLICATION_ID);
        oAuthConfiguration.setApplicationSecret(APPLICATION_SECRET);
        oAuthConfiguration.setRedirectUrl(REDIRECT_URL);
        oAuthConfiguration.setScope(SCOPE);
    }
}