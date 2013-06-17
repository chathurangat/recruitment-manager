package com.hsenidmobile.recruitment.web.service;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.ConfigDecoratorMapper;
import com.opensymphony.module.sitemesh.mapper.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class ConfigDecoratorMapperSpringMvcSupport extends ConfigDecoratorMapper {

    private static final Logger logger = LoggerFactory.getLogger(ConfigDecoratorMapperSpringMvcSupport.class);

    private ConfigLoader configLoader = null;

    /** Create new ConfigLoader using '/WEB-INF/decorators.xml' file. */
    public void init(Config config, Properties properties, DecoratorMapper parent) throws InstantiationException {
        System.out.println("init()...");
        super.init(config, properties, parent);
        try {
            String fileName = properties.getProperty("config", "/WEB-INF/decorators.xml");
            configLoader = new ConfigLoader(fileName, config);
        }
        catch (Exception e) {
            throw new InstantiationException(e.toString());
        }
    }

    /** Retrieve {@link com.opensymphony.module.sitemesh.Decorator} based on 'pattern' tag. */
    public Decorator getDecorator(HttpServletRequest request, Page page) {
        System.out.println("getDecorator()...");
        String thisPath = request.getServletPath();
        System.out.println("\tThisPath: " + thisPath);
        String requestURI = request.getRequestURI();
        System.out.println("\t\tGet request URI: " + requestURI);
        System.out.println("\t\tGet context path: " + request.getContextPath());
        //TODO check indexes
        thisPath = "/springURITemplate" + requestURI.substring(request.getContextPath().length(), requestURI.length());
        System.out.println("\t\t\tThisPath: " + thisPath);
        String name = null;
        try {
            name = configLoader.getMappedName(thisPath);
        }
        catch (ServletException e) {
            e.printStackTrace();
        }
        System.out.println("\tResolved decorator name: " + name);
        Decorator result = getNamedDecorator(request, name);
        System.out.println("Decorator is null ? " + (result == null));
        return result == null ? super.getDecorator(request, page) : result;
    }
}
