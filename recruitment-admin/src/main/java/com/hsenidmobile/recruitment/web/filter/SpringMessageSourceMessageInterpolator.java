package com.hsenidmobile.recruitment.web.filter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import javax.validation.MessageInterpolator;
import java.util.Locale;
import java.util.TimeZone;

public class SpringMessageSourceMessageInterpolator implements MessageInterpolator,
        MessageSourceAware, InitializingBean {

    private MessageSource messageSource;

    @Override
    public String interpolate(String messageTemplate, Context context) {
        TimeZone locale;
        return messageSource.getMessage(messageTemplate, new Object[]{}, Locale.getDefault());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return messageSource.getMessage(messageTemplate, new Object[]{}, locale);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (messageSource == null) {
            throw new IllegalStateException("MessageSource was not injected, could not initialize "
                    + this.getClass().getSimpleName());
        }
    }

}