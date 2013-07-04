package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.EmailMessage;
import com.hsenidmobile.recruitment.model.EmailTemplate;
import com.hsenidmobile.recruitment.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/email_message")
public class EmailMessageController {

    @Autowired
    private EmailTemplateService emailTemplateService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/email_message_generator",method = RequestMethod.GET)
    private ModelAndView initializeEmailTemplateView(){
        ModelAndView modelAndView = new ModelAndView();
        List<EmailTemplate> emailTemplateList = emailTemplateService.findAllEmailTemplate();
        Map<String,Object> modelObjects = new HashMap<String, Object>();
        modelAndView.setViewName("email-template/email-message-generator");
        modelObjects.put("emailTemplateList", emailTemplateList);
        modelAndView.addAllObjects(modelObjects);
        return modelAndView;
    }



   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/email_message_generator",method = RequestMethod.POST)
    public ModelAndView registerOrUpdateCvTemplate(@Valid EmailMessage emailMessage,BindingResult bindingResult,ModelAndView modelAndView,HttpSession session){

        List<EmailTemplate> selectedEmailTemplateSectionList = new ArrayList<EmailTemplate>();
        Map<String,String> errorMessages = new HashMap<String,String>();
        this.findSelectedEmailSectionListForEmailTemplate(emailMessage, selectedEmailTemplateSectionList, errorMessages);
      /*  if(errorMessages.size()>0){
            //binding each error message to the relevant field
            for(Map.Entry<String,String> entry:errorMessages.entrySet()){
                String message = messageSource.getMessage(entry.getValue(),null,currentUserLocale);
                bindingResult.addError(new FieldError("cvApplicationTemplate",entry.getKey(),message));
            }
        }  */

        if(!bindingResult.hasErrors()){
            emailMessage.setEmailSectionList(selectedEmailTemplateSectionList);    //edit up to this*************************
                if(emailMessage.getId()!=null){
                session.setAttribute("last-created-cv-template-id", emailMessage.getId());
            }
        }
        else{
            modelAndView = this.initializeEmailTemplateView();
        }
        return modelAndView;
    }



    private void findSelectedEmailSectionListForEmailTemplate(EmailMessage emailMessage,List<EmailTemplate> selectedEmailTemplateSectionList,Map<String,String> errorMessages){
        //this map will hold a set of entered priorities for each cv application section
        Map<Integer,Integer> enteredPriorities = new HashMap<Integer, Integer>();
        for(int index=0;index<emailMessage.getEmailSectionList().size();index++){
            EmailTemplate emailTemplate = emailMessage.getEmailSectionList().get(index);
            if(emailTemplate.getId()!=null){
                //finding the original instance by giving Id
                EmailTemplate emailTemplate1 = emailTemplateService.findEmailTemplateById(emailTemplate.getId());
                if(emailTemplate1!=null){
                    //setting up the user entered priority
                    selectedEmailTemplateSectionList.add(emailTemplate1);
                }
            }
        }
        //check whether user has selected at least one cv section fr the cv template
        if(selectedEmailTemplateSectionList.size()==0){
            //adding custom error message here
            errorMessages.put("cvApplicationSectionList","error.cv_template_registration.cv.section.should.be.selected");
        }
    }



}
