package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.TextAreaDictionary;
import com.hsenidmobile.recruitment.model.TextFieldDictionary;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class CvFieldDictionaryController {

    private static final Logger logger = LoggerFactory.getLogger(CvFieldDictionaryController.class);

    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;


    /**
     * <p>
     *     display the TextField Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "textfield-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textfield/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldTextFieldRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section textfield dictionary registration view ");
        modelAndView.setViewName("cv-template/textfield-dictionary-register");
        modelAndView.addObject(new TextFieldDictionary());
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the TextFieldDictionary items into ApplicationFieldDictionary collections
     *     Then display the TextField Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "textfield-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textfield/register",method = RequestMethod.POST)
    public ModelAndView registerNewTextFieldDictionary(@Valid TextFieldDictionary textFieldDictionary,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section field dictionary");
        modelAndView.setViewName("cv-template/textfield-dictionary-register");
        if(StringUtils.hasText(textFieldDictionary.getId())){
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(textFieldDictionary);
        }
        else
        {
            cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(textFieldDictionary);
        }
        return modelAndView;
    }


    /**
     * <p>
     *     display the TextArea Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "textarea-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textarea/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldTextAreaRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section textarea registration view ");
        modelAndView.setViewName("cv-template/textarea-dictionary-register");
        modelAndView.addObject(new TextAreaDictionary());
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the TextAreaDictionary items into ApplicationFieldDictionary collections
     *     Then display the TextField Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "textarea-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textarea/register",method = RequestMethod.POST)
    public ModelAndView registerNewTextAreaDictionary(@Valid TextAreaDictionary textAreaDictionary,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section textarea field dictionary");
        modelAndView.setViewName("cv-template/textarea-dictionary-register");
        if(StringUtils.hasText(textAreaDictionary.getId())){
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(textAreaDictionary);
        }
        else
        {
            cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(textAreaDictionary);
        }
        return modelAndView;
    }

}
