package com.hms.oauth.example;

import com.hms.oauth.config.OAuthConfiguration;
import com.hms.oauth.exception.OAuthException;
import com.hms.oauth.http.OAuthResponse;
import com.hms.oauth.provider.BitlyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static com.hms.oauth.config.OAuthKeyBox.ACCESS_TOKEN;

public class BitlyExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitlyExample.class);

    private static final String APPLICATION_ID = "app_id_should_be_here";
    private static final String APPLICATION_SECRET = "app_secret_should_be_here";
    private static final String REDIRECT_URL = "redirect_url_should_be_here";
    private static final String STATE = "state";

    public static void main(String[] args) {

        System.out.println("Bitly OAuth work flow");

        OAuthConfiguration oAuthConfiguration =  new OAuthConfiguration();
        oAuthConfiguration.setApplicationId(APPLICATION_ID);
        oAuthConfiguration.setApplicationSecret(APPLICATION_SECRET);
        oAuthConfiguration.setRedirectUrl(REDIRECT_URL);
        oAuthConfiguration.setState(STATE);

        System.out.println("Getting Authorization URL for the Bitly network ");

        BitlyProvider bitlyProvider = new BitlyProvider(oAuthConfiguration);

        try {
            String bitlyAuthorizationUrl = bitlyProvider.getAuthorizationUrl();
            System.out.println("Authorization URL for the bitly network ["+bitlyAuthorizationUrl+"]");

            System.out.println("Enter request token here >>");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String requestToken = reader.readLine();
            System.out.println(" request token received as user input ["+requestToken+"]\n\n");

            OAuthResponse accessTokenResponse = bitlyProvider.getAccessTokenForRequestToken(requestToken);

            String  accessToken = (String)accessTokenResponse.getResponseParameters().get(ACCESS_TOKEN);
            System.out.println(" Access token received ["+accessToken+"] \n\n");

            System.out.println(" getting the protected resource .....\n\n");

            OAuthResponse protectedResourceResponse = bitlyProvider.getProtectedResource(accessTokenResponse);

            System.out.println("displaying the protected resources retrieved ...... \n\n");
            Map<Object,Object> protectedResourceData  =   protectedResourceResponse.getResponseParameters();
            for(Map.Entry<Object,Object> entry:protectedResourceData.entrySet()){
                System.out.println(" resource key ["+entry.getKey()+"] and resource value ["+entry.getValue()+"]");
            }

        } catch (OAuthException e) {
            LOGGER.error(" OAuthException occurred and oauth exception message [{}]", e.getExceptionMessage());
            LOGGER.error(" OAuthException occurred and exception message [{}]", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("exception occurred while getting request token from user [{}]", e.getMessage());
        }
    }
}
