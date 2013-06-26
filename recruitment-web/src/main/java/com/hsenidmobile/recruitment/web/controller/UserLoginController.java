/**
 *
 */
package com.hsenidmobile.recruitment.web.controller;


import com.hms.oauth.config.OAuthConfiguration;
import com.hms.oauth.config.OAuthKeyBox;
import com.hms.oauth.exception.OAuthException;
import com.hms.oauth.provider.FacebookProvider;
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
    private static final String APPLICATION_ID = "673816255977332";
    private static final String APPLICATION_SECRET = "1b872f85587926cdfce11200aed7c269";
    private static final String REDIRECT_URL = "http://localhost:8080/recruitment-web/user/auth/facebook";
    private static final String STATE = "123";


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
        FacebookProvider facebookProvider = new FacebookProvider(oAuthConfiguration);
        String facebookLoginUrl = facebookProvider.getAuthorizationUrl();
        model.put("facebookLoginUrl",facebookLoginUrl);
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
     * the purpose of this method is to authenticate the facebook users with spring security
     *</p>
     * @param request  as {@link HttpServletRequest}
     * @param modelMap as {@link ModelMap}
     * @return logical view name for the home page or login page pon user authentication as {@link String}
     */
    @RequestMapping(value = "/facebook",method = RequestMethod.GET)
    public ModelAndView facebookUserAuthentication(HttpServletRequest request,ModelMap modelMap){

        String defaultFacebookPassword = "admin";
        String defaultFacebookPasswordInMd5Hashed = "21232f297a57a5a743894a0e4a801fc3";

        ModelAndView modelAndView = new ModelAndView();

        if(request!=null && request.getAttribute(OAuthKeyBox.OAUTH_STATUS).equals("success")){
            logger.info(" starting the spring security integration with facebook");
            //check whether the facebook user has accessed this website  previousl
            String facebookUsername = (String)request.getAttribute(OAuthKeyBox.USERNAME);
            String facebookUserId = (String)request.getAttribute(OAuthKeyBox.ID);

            Applicant applicant =  applicantService.findApplicantFromSocialNetworkDetails("facebook",facebookUsername,facebookUserId,true);

            if(applicant==null){
                applicant =  new Applicant();
                applicant.setDefaultMd5HashedPassword(defaultFacebookPasswordInMd5Hashed);
                System.out.println("first time user ["+facebookUsername+"] accessing the recruitment portal");
                //registering the applicant details
                this.registerNewApplicant(applicant,request);
            }
            //sending for user authentication
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(facebookUsername, defaultFacebookPassword);
            UserDetails user = new User("username", "password", true, true, true, true,new ArrayList<GrantedAuthority>());
            token.setDetails(user);
            try {
                Authentication auth = authenticationManager.authenticate(token);
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.debug(" Login succeeded! for the user [{}]",username);
                System.out.println(" Login succeeded! for the user [{}]"+username);
                //todo move session keys to common class
                request.getSession().setAttribute("username",facebookUsername);
                request.getSession().setAttribute("user-id",applicant.getId());
                modelAndView.setViewName("welcome-redirect");
            } catch (BadCredentialsException e) {
                logger.info("error ocured "+e);
                logger.debug(" exception occurred while authenticating the user and exception message [{}]",e.getMessage());
                modelAndView.setViewName("open_id_login_page");
            }
        }
        else{
            if(request!=null && request.getAttribute(OAuthKeyBox.OAUTH_STATUS).equals("error")){
                modelMap.put("error",request.getAttribute(OAuthKeyBox.OAUTH_MESSAGE));
            }
            modelAndView.setViewName("open_id_login_page");
        }
        return modelAndView;
    }


    /**
     * <p>
     *     registering new facebook user upon his first access of the recruitment-portal
     * </p>
     * @param applicant as {@link Applicant}
     * @param request as {@link HttpServletRequest}
     */
    private void registerNewApplicant(Applicant applicant,HttpServletRequest request){
        applicant.setUsername(request.getAttribute(OAuthKeyBox.USERNAME).toString());
        applicant.setFirstName(request.getAttribute(OAuthKeyBox.FIRST_NAME).toString());
        applicant.setLastName(request.getAttribute(OAuthKeyBox.LAST_NAME).toString());
        applicant.setOpenIdProvider("facebook");
        applicant.setHomeTown(request.getAttribute(OAuthKeyBox.HOME_TOWN).toString());
        applicant.setGender(request.getAttribute(OAuthKeyBox.GENDER).toString());
        applicant.setName(request.getAttribute(OAuthKeyBox.NAME).toString());
        applicant.setLocation(request.getAttribute(OAuthKeyBox.LOCATION).toString());
        applicant.setSocialNetworkResourceId(request.getAttribute(OAuthKeyBox.ID).toString());
        applicant.setStatus(true);
        applicantService.addApplicant(applicant);
        logger.info("applicant was saved ");
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
        oAuthConfiguration.setState(STATE);
    }
}