package com.hsenidmobile.recruitment.web.filter;

import com.hms.oauth.config.OAuthConfiguration;
import com.hms.oauth.exception.OAuthException;
import com.hms.oauth.http.OAuthResponse;
import com.hms.oauth.provider.FacebookProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import static com.hms.oauth.config.OAuthKeyBox.*;

public class FacebookOAuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(FacebookOAuthFilter.class);

    private FilterConfig filterConfig;

    private OAuthConfiguration oAuthConfiguration;

    private static final String APPLICATION_ID = "673816255977332";
    private static final String APPLICATION_SECRET = "1b872f85587926cdfce11200aed7c269";
    private static final String REDIRECT_URL = "http://localhost:8080/recruitment-web/user/auth/facebook";
    private static final String STATE = "123";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
        this.initializeOAuthConfiguration();
    }

    //todo remove sout
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Map parameterMap = (Map<String,String>) request.getParameterMap();
        if(parameterMap.containsKey(CODE)){
            logger.info(" successfully received the request token => [{}]",parameterMap.get(CODE));
            System.out.println(" successfully received the request token => ["+parameterMap.get(CODE)+"]");
            //getting the access token
            FacebookProvider facebookProvider =  new FacebookProvider(oAuthConfiguration);

            try {
                String requestToken = request.getParameter(CODE);
                System.out.println(" request token received  ["+requestToken+"]");
                OAuthResponse accessTokenResponse = facebookProvider.getAccessTokenForRequestToken(requestToken);

                String  accessToken = (String)accessTokenResponse.getResponseParameters().get(ACCESS_TOKEN);
                System.out.println(" Access token received ["+accessToken+"] ");
                logger.debug(" Access token received [{}] ", accessToken);

                System.out.println(" getting the protected resource .....");
                logger.debug(" getting the protected resource .....");

                OAuthResponse protectedResourceResponse = facebookProvider.getProtectedResource(accessTokenResponse);

                System.out.println("displaying the protected resources retrieved ...... ");
                Map<Object,Object> protectedResourceData  =   protectedResourceResponse.getResponseParameters();

                if(protectedResourceData!=null){
                    request.setAttribute(OAUTH_STATUS,"success");

                    for(Map.Entry<Object,Object> entry:protectedResourceData.entrySet()){
                        System.out.println(" resource key ["+entry.getKey()+"] and resource value ["+entry.getValue()+"]");
                        logger.debug(" resource key [[]] and resource value [{}]",entry.getKey(),entry.getValue());
                        request.setAttribute(entry.getKey().toString(),entry.getValue());
                    }
                }

            } catch (OAuthException e) {
                System.out.println("oauth exception occurred ["+e.getExceptionMessage()+"]");
                logger.debug("oauth exception occurred [{}]",e.getExceptionMessage());
                request.setAttribute(OAUTH_STATUS, "error");
                request.setAttribute(OAUTH_MESSAGE,"unexpected error occurred with facebook... please try again after few seconds");
            }

        }
        else if(parameterMap.containsKey(ERROR) && parameterMap.get(ERROR).equals(ACCESS_DENIED)){
            //todo check whether why this logic is not working
            System.out.println(" User has denied the access for the facebook");
            logger.info(" User has denied the access for the facebook");
            request.setAttribute(OAUTH_STATUS,"error");
            request.setAttribute(OAUTH_MESSAGE,"user has denied the access through facebook");
        }
        else{
//            System.out.println(" ["+request.getParameter(ERROR)+"]");
//            System.out.println(" unexpected error occurred with facebook");
//            logger.info(" unexpected error occurred with facebook");
//            request.setAttribute(OAUTH_STATUS,"error");
//            request.setAttribute(OAUTH_MESSAGE,"unexpected error occurred with facebook... please try again after few seconds");
            System.out.println(" User has denied the access for the facebook");
            logger.info(" User has denied the access for the facebook");
            request.setAttribute(OAUTH_STATUS,"error");
            request.setAttribute(OAUTH_MESSAGE,"user has denied the access through facebook");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info(" executing FacebookOAuthFilter destroy method");
        System.out.println(" executing FacebookOAuthFilter destroy method");
    }


    private void initializeOAuthConfiguration(){
        //setting up oauth configurations
        oAuthConfiguration = new OAuthConfiguration();
        oAuthConfiguration.setApplicationId(APPLICATION_ID);
        oAuthConfiguration.setApplicationSecret(APPLICATION_SECRET);
        oAuthConfiguration.setRedirectUrl(REDIRECT_URL);
        oAuthConfiguration.setState(STATE);
    }
}
