package com.hms.oauth.api.impl;

import com.hms.oauth.api.OAuth2;
import com.hms.oauth.config.OAuthConfiguration;

public abstract class OAuth2Impl implements OAuth2 {

    private OAuthConfiguration oauthConfiguration;

    public OAuthConfiguration getOAuthConfiguration() {
        return oauthConfiguration;
    }

    public void setOAuthConfiguration(OAuthConfiguration oauthConfiguration) {
        this.oauthConfiguration = oauthConfiguration;
    }
}
