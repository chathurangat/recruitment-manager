package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.DropDownDictionary;
import com.hsenidmobile.recruitment.model.DropDownOption;
import com.hsenidmobile.recruitment.model.TextAreaDictionary;
import com.hsenidmobile.recruitment.model.TextFieldDictionary;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.DropdownOptionsService;
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
@RequestMapping(value = "/cv_field_dictionary")
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
//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textfield/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldTextFieldRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section textfield field dictionary  registration view ");
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
//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textfield/register",method = RequestMethod.POST)
    public ModelAndView registerNewTextFieldDictionary(@Valid TextFieldDictionary textFieldDictionary,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section textfield field dictionary ");
        modelAndView.setViewName("cv-template/textfield-dictionary-register");
        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(textFieldDictionary.getId())){
                cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(textFieldDictionary);
                logger.info("registering new cv template section textfield field dictionary form contains no errors (update) ");
            }
            else
            {
                cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(textFieldDictionary);
                logger.info("registering new cv template section textfield field dictionary form contains no errors (create) ");
            }
            return modelAndView;
        }
        else {
            logger.info("registering new cv template section textfield field dictionary form contains errors ");
            return modelAndView;
        }

    }


    /**
     * <p>
     *     display the TextArea Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "textarea-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
  //  @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textarea/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldTextAreaRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section textarea field dictionary registration view ");
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
   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textarea/register",method = RequestMethod.POST)
    public ModelAndView registerNewTextAreaDictionary(@Valid TextAreaDictionary textAreaDictionary,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section textarea field dictionary");
        modelAndView.setViewName("cv-template/textarea-dictionary-register");
        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(textAreaDictionary.getId())){
                cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(textAreaDictionary);
                logger.info("registering new cv template section textarea field dictionary form contains no errors (update) ");
            }
            else
            {
                cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(textAreaDictionary);
                logger.info("registering new cv template section textarea field dictionary form contains no errors (create) ");
            }
            return modelAndView;
        }
        else {
            logger.info("registering new cv template section textarea field dictionary form contains errors ");
            return modelAndView;
        }

    }

    /**
     * <p>
     *     display the DropDown Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "dropdown-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_dropdown/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldDropdownRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section dropdown field dictionary registration view ");
        modelAndView.setViewName("cv-template/dropdown-dictionary-register");
        modelAndView.addObject(new DropDownDictionary());
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the DropDown Dictionary items into ApplicationFieldDictionary collections
     *     Then display the DropDown Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "dropdown-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_dropdown/register",method = RequestMethod.POST)
    public ModelAndView registerNewDropDownDictionary(@Valid DropDownDictionary dropDownDictionary,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section dropdown field dictionary");
        modelAndView.setViewName("cv-template/dropdown-dictionary-register");
        if(StringUtils.hasText(dropDownDictionary.getId())){
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(dropDownDictionary);
        }
        else
        {
            cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(dropDownDictionary);
        }
        return modelAndView;
    }

     @Autowired
     private DropdownOptionsService dropdownOptionsService;

    /**
     * <p>
     *     display the DropDown Options Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "dropdown-dictionary-options-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_dropdown-options/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldDropdownOptionsRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section dropdown field options registration view ");
        modelAndView.setViewName("cv-template/dropdown-dictionary-options-register");
        modelAndView.addObject("optionsList", dropdownOptionsService.findAllDropDownOption());
        modelAndView.addObject(new DropDownOption());
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the DropDown Options items into DropDownOptionsDictionary collections
     *     Then display the DropDown Options Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "dropdown-dictionary-options-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_dropdown-options/add",method = RequestMethod.POST)
    public ModelAndView registerNewDropDownOptions(@Valid DropDownOption dropDownOption,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section dropdown field options");
        modelAndView.setViewName("cv-template/dropdown-dictionary-options-register");
        modelAndView.addObject("optionsList", dropdownOptionsService.findAllDropDownOption());

        if(StringUtils.hasText(dropDownOption.getId())){
            dropdownOptionsService.insertDropDownOption(dropDownOption);
        }
        else
        {
            dropdownOptionsService.createDropDownOption(dropDownOption);
        }
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_dropdown-options/delete",method = RequestMethod.GET)
    public ModelAndView deleteDropDownOption(@Valid DropDownOption dropDownOption,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" deleting cv template section dropdown field option");
        modelAndView.setViewName("cv-template/dropdown-dictionary-options-register");
        modelAndView.addObject("optionsList", dropdownOptionsService.findAllDropDownOption());

        dropdownOptionsService.removeDropDownOption(dropDownOption);

        return  modelAndView;
    }
}
