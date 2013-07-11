package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.dao.ApplicationFieldDictionaryValidationDao;
import com.hsenidmobile.recruitment.model.*;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.DropdownOptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/cv_field_dictionary")
public class CvFieldDictionaryController {

    private static final Logger logger = LoggerFactory.getLogger(CvFieldDictionaryController.class);

    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;
    @Autowired
    private MessageSource messageSource;
    //todo move to service layer
    @Autowired
    private ApplicationFieldDictionaryValidationDao applicationFieldDictionaryValidationDao;

    @Autowired
    private DropdownOptionsService dropdownOptionsService;

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
        logger.info(" display cv template section textfield field dictionary  registration view ");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        modelsMap.put("textFieldDictionary",new TextFieldDictionary());
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("validationList",validationList);
        modelAndView.addObject(new TextFieldDictionary());
        modelAndView.addAllObjects(modelsMap);
        modelAndView.setViewName("cv-field-dictionary/textfield-dictionary-register");
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
    public ModelAndView registerNewTextFieldDictionary(@Valid TextFieldDictionary textFieldDictionary,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession){
        System.out.println(" registering new cv template section textfield field dictionary ");
        modelAndView.setViewName("cv-field-dictionary/textfield-dictionary-register");
        modelAndView.addObject("validationList",this.getValidationCriteriaList());

        List<ApplicationFieldDictionaryValidation> userSelectedValidations = this.getUserSubmittedValidations(textFieldDictionary.getApplicationFieldDictionaryValidationList());
        logger.info(" size of user  selected validations [{}]",userSelectedValidations.size());

        List<String> errorMessages = this.validateAppliedValidations(userSelectedValidations);

        if(errorMessages!=null && errorMessages.size()>0){
            logger.info("size of error messages [{}]",errorMessages.size());
            Locale userLocale = this.getUserLocale(httpSession);
            for(String errorKey:errorMessages){
                bindingResult.addError(new FieldError("cvApplicationTemplate","applicationFieldDictionaryValidationList",messageSource.getMessage(errorKey,null,userLocale)));
            }
        }
        else {
            textFieldDictionary.setApplicationFieldDictionaryValidationList(userSelectedValidations);
        }

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
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_textarea/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldTextAreaRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section textarea field dictionary registration view ");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("textAreaDictionary",new TextAreaDictionary());
        modelsMap.put("validationList",validationList);
        modelAndView.addAllObjects(modelsMap);
        modelAndView.setViewName("cv-field-dictionary/textarea-dictionary-register");
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
    public ModelAndView registerNewTextAreaDictionary(@Valid TextAreaDictionary textAreaDictionary,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession){
        System.out.println(" registering new cv template section textarea field dictionary");
        modelAndView.setViewName("cv-field-dictionary/textarea-dictionary-register");
        modelAndView.addObject("validationList",this.getValidationCriteriaList());

        List<ApplicationFieldDictionaryValidation> userSelectedValidations = this.getUserSubmittedValidations(textAreaDictionary.getApplicationFieldDictionaryValidationList());
        logger.info(" size of user  selected validations [{}]",userSelectedValidations.size());

        List<String> errorMessages = this.validateAppliedValidations(userSelectedValidations);

        if(errorMessages!=null && errorMessages.size()>0){
            logger.info("size of error messages [{}]",errorMessages.size());
            Locale userLocale = this.getUserLocale(httpSession);
            for(String errorKey:errorMessages){
                bindingResult.addError(new FieldError("cvApplicationTemplate","applicationFieldDictionaryValidationList",messageSource.getMessage(errorKey,null,userLocale)));
            }
        }
        else {
            textAreaDictionary.setApplicationFieldDictionaryValidationList(userSelectedValidations);
        }

        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(textAreaDictionary.getId())){
                cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(textAreaDictionary);
                logger.info("registering new cv template section textarea field dictionary form contains no errors (update) ");
                modelAndView.setViewName("cv-field-dictionary/textarea-dictionary-success");

            }
            else
            {
                cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(textAreaDictionary);
                logger.info("registering new cv template section textarea field dictionary form contains no errors (create) ");
                modelAndView.setViewName("cv-field-dictionary/textarea-dictionary-success");

            }
            return modelAndView;
        }
        else {
            logger.info("registering new cv template section textarea field dictionary form contains errors ");
            modelAndView.setViewName("cv-field-dictionary/textarea-dictionary-failed");

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
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        modelsMap.put("dropDownDictionary",new DropDownDictionary());
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("validationList",validationList);
        modelAndView.addAllObjects(modelsMap);
        modelAndView.setViewName("cv-field-dictionary/dropdown-dictionary-register");
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
    public ModelAndView registerNewDropDownDictionary(@Valid DropDownDictionary dropDownDictionary,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession){
        System.out.println(" registering new cv template section dropdown field dictionary");
        modelAndView.setViewName("cv-field-dictionary/dropdown-dictionary-register");
        modelAndView.addObject("validationList",this.getValidationCriteriaList());

        List<ApplicationFieldDictionaryValidation> userSelectedValidations = this.getUserSubmittedValidations(dropDownDictionary.getApplicationFieldDictionaryValidationList());
        logger.info(" size of user  selected validations [{}]",userSelectedValidations.size());

        List<String> errorMessages = this.validateAppliedValidations(userSelectedValidations);

        if(errorMessages!=null && errorMessages.size()>0){
            logger.info("size of error messages [{}]",errorMessages.size());
            Locale userLocale = this.getUserLocale(httpSession);
            for(String errorKey:errorMessages){
                bindingResult.addError(new FieldError("cvApplicationTemplate","applicationFieldDictionaryValidationList",messageSource.getMessage(errorKey,null,userLocale)));
            }
        }
        else {
            dropDownDictionary.setApplicationFieldDictionaryValidationList(userSelectedValidations);
        }

        if(!bindingResult.hasErrors()){
             if(StringUtils.hasText(dropDownDictionary.getId())){
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(dropDownDictionary);
             }
             else {
            cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(dropDownDictionary);
             }
        return modelAndView;
        }
        else {
            logger.info("registering new cv template section dropdown field dictionary form contains errors ");
            return modelAndView;
    }
}


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
        modelAndView.setViewName("cv-field-dictionary/dropdown-dictionary-options-register");

        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        List<ApplicationFieldDictionary> applicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<String> dropDownList = this.createDropdownList(applicationFieldDictionaryList);
        modelsMap.put("dropDownList",dropDownList);
        System.out.print("DropdownList size is : " + dropDownList.size());
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
        modelAndView.setViewName("cv-field-dictionary/dropdown-dictionary-options-register");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        List<ApplicationFieldDictionary> applicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<String> dropDownList = this.createDropdownList(applicationFieldDictionaryList);
        modelsMap.put("dropDownList",dropDownList);
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        modelsMap.put("applicationFieldDictionary",applicationFieldDictionary);
        modelAndView.addAllObjects(modelsMap);
        if(bindingResult.hasErrors()){
            logger.info(" submitted form contains errors");
           // modelAndView = this.initializeCvTemplateFieldRegistrationView(cvApplicationTemplate);
        }
        else{
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(applicationFieldDictionary);
            modelAndView.setViewName("redirect:cv-field-dictionary/dropdown-dictionary-options-register");
        }
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_dropdown-options/delete",method = RequestMethod.GET)
    public ModelAndView deleteDropDownOption(@Valid DropDownOption dropDownOption,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" deleting cv template section dropdown field option");
        modelAndView.setViewName("cv-field-dictionary/dropdown-dictionary-options-register");
        modelAndView.addObject("optionsList", dropdownOptionsService.findAllDropDownOption());

        dropdownOptionsService.removeDropDownOption(dropDownOption);

        return  modelAndView;
    }


    /**
     * <p>
     *     display the RadioButton Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "radiobutton-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_radiobutton/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldRadioButtonRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section RadioButton field dictionary registration view ");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        modelsMap.put("radioButtonDictionary",new RadioButtonDictionary());
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("validationList",validationList);
        modelAndView.addAllObjects(modelsMap);

        modelAndView.setViewName("cv-field-dictionary/radiobutton-dictionary-register");
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the RadioButton Dictionary items into ApplicationFieldDictionary collections
     *     Then display the RadioButton Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "radiobutton-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_radiobutton/register",method = RequestMethod.POST)
    public ModelAndView registerNewRadioButtonDictionary(@Valid RadioButtonDictionary radioButtonDictionary,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession){
        System.out.println(" registering new cv template section radiobutton field dictionary");
        modelAndView.setViewName("cv-field-dictionary/radiobutton-dictionary-register");
        modelAndView.addObject("validationList",this.getValidationCriteriaList());
        List<ApplicationFieldDictionaryValidation> userSelectedValidations = this.getUserSubmittedValidations(radioButtonDictionary.getApplicationFieldDictionaryValidationList());
        logger.info(" size of user  selected validations [{}]",userSelectedValidations.size());

        List<String> errorMessages = this.validateAppliedValidations(userSelectedValidations);

        if(errorMessages!=null && errorMessages.size()>0){
            logger.info("size of error messages [{}]",errorMessages.size());
            Locale userLocale = this.getUserLocale(httpSession);
            for(String errorKey:errorMessages){
                bindingResult.addError(new FieldError("cvApplicationTemplate","applicationFieldDictionaryValidationList",messageSource.getMessage(errorKey,null,userLocale)));
            }
        }
        else {
            radioButtonDictionary.setApplicationFieldDictionaryValidationList(userSelectedValidations);
        }

        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(radioButtonDictionary.getId())){
                cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(radioButtonDictionary);
            }
            else
            {
                cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(radioButtonDictionary);
            }
            return modelAndView;
        }
        else {
            logger.info("registering new cv template section radio button field dictionary form contains errors ");
            return modelAndView;
        }
    }

    /**
     * <p>
     *     display the radiobutton value Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "radiobutton-dictionary-values-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_radiobutton-values/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldRadioButtonValuesRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section radio button values registration view ");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        modelsMap.put("radioButtonValue",new RadioButtonValue());
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("validationList",validationList);
        List<ApplicationFieldDictionary> applicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<String>radiobuttonList = this.createRadiobuttonList(applicationFieldDictionaryList);
        modelsMap.put("radiobuttonList",radiobuttonList);
        modelAndView.addAllObjects(modelsMap);
        System.out.print("radio button list size : " + radiobuttonList.size());
        modelAndView.setViewName("cv-field-dictionary/radiobutton-dictionary-values-register");
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the radio button values items into ApplicationFieldDictionary collections
     *     Then display the radio button values Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "radiobutton-dictionary-options-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_radiobutton-values/add",method = RequestMethod.POST)
    public ModelAndView registerNewRadioButtonValues(@Valid RadioButtonValue radioButtonValue,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section radiobutton field values");
        modelAndView.setViewName("cv-field-dictionary/radiobutton-dictionary-values-register");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        modelsMap.put("applicationFieldDictionary",applicationFieldDictionary);
        modelAndView.addAllObjects(modelsMap);
        if(bindingResult.hasErrors()){
            logger.info(" submitted form contains errors");
            // modelAndView = this.initializeCvTemplateFieldRegistrationView(cvApplicationTemplate);
        }
        else{
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(applicationFieldDictionary);
            modelAndView.setViewName("redirect:cv-field-dictionary/radiobutton-dictionary-values-register");
        }
        return modelAndView;
    }

    /**
     * <p>
     *     display the CheckBox Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "checkbox-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_checkbox/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldCheckBoxRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section CheckBox field dictionary registration view ");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        modelsMap.put("checkBoxDictionary",new CheckBoxDictionary());
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("validationList",validationList);
        modelAndView.addAllObjects(modelsMap);

        modelAndView.setViewName("cv-field-dictionary/checkbox-dictionary-register");
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the CheckBox Dictionary items into ApplicationFieldDictionary collections
     *     Then display the CheckBox Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "checkbox-dictionary-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_checkbox/register",method = RequestMethod.POST)
    public ModelAndView registerNewCheckBoxDictionary(@Valid CheckBoxDictionary checkBoxDictionary,BindingResult bindingResult,ModelAndView modelAndView,HttpSession httpSession){
        System.out.println(" registering new cv template section checkbox field dictionary");
        modelAndView.setViewName("cv-field-dictionary/checkbox-dictionary-register");
        modelAndView.addObject("validationList",this.getValidationCriteriaList());

        List<ApplicationFieldDictionaryValidation> userSelectedValidations = this.getUserSubmittedValidations(checkBoxDictionary.getApplicationFieldDictionaryValidationList());
        logger.info(" size of user  selected validations [{}]",userSelectedValidations.size());

        List<String> errorMessages = this.validateAppliedValidations(userSelectedValidations);

        if(errorMessages!=null && errorMessages.size()>0){
            logger.info("size of error messages [{}]",errorMessages.size());
            Locale userLocale = this.getUserLocale(httpSession);
            for(String errorKey:errorMessages){
                bindingResult.addError(new FieldError("cvApplicationTemplate","applicationFieldDictionaryValidationList",messageSource.getMessage(errorKey,null,userLocale)));
            }
        }
        else {
            checkBoxDictionary.setApplicationFieldDictionaryValidationList(userSelectedValidations);
        }
        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(checkBoxDictionary.getId())){
                cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(checkBoxDictionary);
            }
            else
            {
                cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(checkBoxDictionary);
            }
            return modelAndView;
        }
        else {
            logger.info("registering new cv template section check box field dictionary form contains errors ");
            return modelAndView;
        }
    }

    /**
     * <p>
     *     display the checkbox value Dictionary Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "checkbox-dictionary-values-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_checkbox-values/registration_view",method = RequestMethod.GET)
    public ModelAndView cvFieldCheckBoxValuesRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section checkbox values registration view ");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        modelsMap.put("checkBoxValue",new CheckBoxValue());
        List<ApplicationFieldDictionaryValidation> validationList = this.getValidationCriteriaList();
        modelsMap.put("validationList",validationList);

        List<ApplicationFieldDictionary> applicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<String> checkboxList = this.createCheckboxList(applicationFieldDictionaryList);
        modelsMap.put("checkboxList",checkboxList);
        System.out.print("checkList size is : " + checkboxList.size() + checkboxList.get(0) );
        modelAndView.addAllObjects(modelsMap);
        modelAndView.setViewName("cv-field-dictionary/checkbox-dictionary-values-register");
        return modelAndView;
    }

    /**
     * <p>
     *     Insert the checkbox values items into ApplicationFieldDictionary collections
     *     Then display the checkbox values Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "checkbox-dictionary-options-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_checkbox-values/add",method = RequestMethod.POST)
    public ModelAndView registerNewCheckBoxValues(@Valid CheckBoxValue checkBoxValue,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section checkbox field values");
        modelAndView.setViewName("cv-field-dictionary/checkbox-dictionary-values-register");
        Map<String,Object> modelsMap =  new HashMap<String, Object>();
        List<ApplicationFieldDictionary> applicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<String> checkboxList = this.createCheckboxList(applicationFieldDictionaryList);
        modelsMap.put("checkboxList",checkboxList);
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        modelsMap.put("applicationFieldDictionary",applicationFieldDictionary);
        modelAndView.addAllObjects(modelsMap);
        if(bindingResult.hasErrors()){
            logger.info(" submitted form contains errors");
            // modelAndView = this.initializeCvTemplateFieldRegistrationView(cvApplicationTemplate);
        }
        else{
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(applicationFieldDictionary);
            modelAndView.setViewName("redirect:cv-field-dictionary/checkbox-dictionary-values-register");
        }
        return modelAndView;
    }


    private List<ApplicationFieldDictionaryValidation> getValidationCriteriaList(){
//        List<ApplicationFieldDictionaryValidation> validationList =new ArrayList<ApplicationFieldDictionaryValidation>();
//        ApplicationFieldDictionaryValidation  requiredValidation = new ApplicationFieldDictionaryValidation();
//        requiredValidation.setValidationCriteria("REQUIRED");
//        requiredValidation.setId("1");
//        validationList.add(requiredValidation);
//
//        ApplicationFieldDictionaryValidation  emailValidation = new ApplicationFieldDictionaryValidation();
//        emailValidation.setValidationCriteria("EMAIL");
//        emailValidation.setId("2");
//        validationList.add(emailValidation);
//
//        ApplicationFieldDictionaryValidation  phoneNumberValidation = new ApplicationFieldDictionaryValidation();
//        phoneNumberValidation.setValidationCriteria("PHONE");
//        phoneNumberValidation.setId("3");
//        validationList.add(phoneNumberValidation);

        return applicationFieldDictionaryValidationDao.getAllValidationCriteria();
    }

//
//    private List<String> validateAppliedValidations(List<ApplicationFieldDictionaryValidation> applicationFieldDictionaryValidationList){
//        List<String> errorMessages = new ArrayList<String>();
//        if(applicationFieldDictionaryValidationList==null || applicationFieldDictionaryValidationList.size()==0){
//            errorMessages.add("error.textfield.registration.validation.criteria.required");
//        }
//        else{
//            int initialSizeOfValidationList = applicationFieldDictionaryValidationList.size();
//            //the following references will not be modified
//            logger.info(" initial size [{}]",initialSizeOfValidationList);
//            for(int index=0;index<=initialSizeOfValidationList;index++){
//                logger.info("index [{}]",index);
//                ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation = applicationFieldDictionaryValidationList.get(index);
//                if(applicationFieldDictionaryValidation!=null && applicationFieldDictionaryValidation.getId()!=null){
//                    logger.info( " id [{}] and value [{}]",applicationFieldDictionaryValidation.getId(),applicationFieldDictionaryValidation.getValidationCriteria());
//                }
//                else {
//                    logger.info(" removing the index [{}]",index);
//                    applicationFieldDictionaryValidationList.remove(index);
//                }
//            }
//            logger.info(" submitted form size [{}]",applicationFieldDictionaryValidationList.size());
//        }
//
//        return errorMessages;
//    }



    private List<String> validateAppliedValidations(List<ApplicationFieldDictionaryValidation> userSelectedValidationsList){
        List<String> errorMessages = new ArrayList<String>();

        if(userSelectedValidationsList!=null && userSelectedValidationsList.size()==0){
            errorMessages.add("error.textfield.registration.validation.criteria.required");
        }
        return errorMessages;
    }


    /**
     * <p>
     *     getting the user submitted  validations from the validations available for the text fields
     * </p>
     * @return
     */
    public List<ApplicationFieldDictionaryValidation> getUserSubmittedValidations(List<ApplicationFieldDictionaryValidation> availableValidationForTextfield){

        List<ApplicationFieldDictionaryValidation> userSubmittedValidations = new ArrayList<ApplicationFieldDictionaryValidation>();

        if(availableValidationForTextfield!=null && availableValidationForTextfield.size()!=0){
           for(ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation:availableValidationForTextfield){
               logger.info(" id [{}]",applicationFieldDictionaryValidation.getId());
               if(applicationFieldDictionaryValidation!=null && applicationFieldDictionaryValidation.getId()!=null){
                 //finding the master data instance of the user submitted  validation
                   userSubmittedValidations.add(applicationFieldDictionaryValidationDao.findValidationById(applicationFieldDictionaryValidation.getId()));
               }
           }
        }
       return userSubmittedValidations;
    }


    /**
     * <p>
     *     getting the user locale from the user session
     * </p>
     * @param session as {@link javax.servlet.http.HttpSession}
     * @return current user locale as {@link java.util.Locale}
     */
    private Locale getUserLocale(HttpSession session){
        if(session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)!=null){
            return (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        }
        else{
            //setting up the default locale to english
            return new Locale("en");
        }
    }


    /**
     * <p>
     *     creating the dropdown list based on the applicationFieldDictionaryList
     * </p>
     * @param applicationFieldDictionaryList contains the list of {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary} instances found
     * @return list of priorities based on the size of the cv application section list
     */
    private List<String> createDropdownList(List<ApplicationFieldDictionary> applicationFieldDictionaryList){
        List<String> dropdownList = new ArrayList<String>();
        if(applicationFieldDictionaryList!=null){
            for(int i=0;i<applicationFieldDictionaryList.size();i++){
                if(applicationFieldDictionaryList.get(i).getHtmlComponent().equalsIgnoreCase("dropdown") )
                {
                dropdownList.add(applicationFieldDictionaryList.get(i).getLabelEn());
                }
            }
        }
        System.out.print("dropdwon size is : "+dropdownList.size() );
        return dropdownList;
    }

    /**
     * <p>
     *     creating the checkbox list based on the applicationFieldDictionaryList
     * </p>
     * @param applicationFieldDictionaryList contains the list of {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary} instances found
     * @return list of priorities based on the size of the cv application section list
     */
    private List<String> createCheckboxList(List<ApplicationFieldDictionary> applicationFieldDictionaryList){
        List<String> checkboxList = new ArrayList<String>();
        if(applicationFieldDictionaryList!=null){
            for(int i=0;i< applicationFieldDictionaryList.size();i++){
                if(applicationFieldDictionaryList.get(i).getHtmlComponent().equalsIgnoreCase("checkbox") )
                {
                    checkboxList.add(applicationFieldDictionaryList.get(i).getLabelEn());
                }
            }
        }
        return checkboxList;
    }

    /**
     * <p>
     *     creating the radiobutton list based on the applicationFieldDictionaryList
     * </p>
     * @param applicationFieldDictionaryList contains the list of {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary} instances found
     * @return list of priorities based on the size of the cv application section list
     */
    private List<String> createRadiobuttonList(List<ApplicationFieldDictionary> applicationFieldDictionaryList){
        List<String> radiobuttonList = new ArrayList<String>();
        if(applicationFieldDictionaryList!=null){
            for(int i=0;i< applicationFieldDictionaryList.size();i++){
                if(applicationFieldDictionaryList.get(i).getHtmlComponent().equalsIgnoreCase("radiobutton") )
                {
                    radiobuttonList.add(applicationFieldDictionaryList.get(i).getLabelEn());
                }
            }
        }
        return radiobuttonList;
    }

}
