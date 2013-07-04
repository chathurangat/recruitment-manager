package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.EmailTemplate;
import com.hsenidmobile.recruitment.model.TextFieldDictionary;
import com.hsenidmobile.recruitment.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/2/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */


@Controller
@RequestMapping(value = "/email_template")
public class EmailTemplateController {
    @Autowired
    private EmailTemplateService emailTemplateService;

   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/email_insert_view",method = RequestMethod.GET)
    public ModelAndView emailTemplateInsertView(ModelAndView modelAndView){
       // logger.info(" display cv template section textfield field dictionary  registration view ");
        modelAndView.setViewName("email-template/email-template-insert");
        modelAndView.addObject(new TextFieldDictionary());
        return modelAndView;
    }


    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/email_insert",method = RequestMethod.POST)
    public ModelAndView emailTemplateInsert(@Valid EmailTemplate emailTemplate,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new email template ");
        modelAndView.setViewName("email-template/email-template-insert");
        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(emailTemplate.getId())){
                emailTemplateService.update(emailTemplate);
               // logger.info("registering new cv template section form contains no errors (update) ");
                System.out.println(" there are no errors (update)");
            }
            else
            {
                emailTemplateService.create(emailTemplate);
               // logger.info("registering new cv template section form contains no errors (create) ");
                System.out.println(" there are no errors (create)");
            }
            return modelAndView;
        }
        else {

           // logger.info("registering new cv template section form contains errors ");
            return modelAndView;
        }

    }
    //to get the list in to emai-template-collection.jsp
    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/emailTemplate_Retrive",method = RequestMethod.GET)
    private ModelAndView initializeEmailTemplateView(){
        ModelAndView modelAndView = new ModelAndView();
        List<EmailTemplate> emailTemplateList = emailTemplateService.findAllEmailTemplate();
        Map<String,Object> modelObjects = new HashMap<String, Object>();
        modelAndView.setViewName("email-template/email-template-collection");
        modelObjects.put("emailTemplateList", emailTemplateList);
        modelAndView.addAllObjects(modelObjects);
        return modelAndView;
    }

}


