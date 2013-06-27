package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
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
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cv_template")
public class CvTemplateController {

    private static final Logger  logger = LoggerFactory.getLogger(CvTemplateController.class);

    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;
    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;

    //    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view",method = RequestMethod.GET)
    public ModelAndView CvTemplateRegisterView(ModelAndView modelAndView){
        System.out.println(" cv template registration view ");
        List<CvApplicationSection> cvApplicationSectionList = cvApplicationSectionService.findAllCvSection();
        List<ApplicationFieldDictionary> applicationFieldDictionaryItemsList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
//        if (cvApplicationSectionList!=null){
        Map<String,Object> modelObjects = new HashMap<String, Object>();
//        Map<String,String> cvApplicationSectionMap = new HashMap<String, String>();
//        cvApplicationSectionMap.put("1n","section one");
//        cvApplicationSectionMap.put("2n","section two");
//        cvApplicationSectionMap.put("3n","section three");
        List<Long> priorityList = new ArrayList<Long>();
        if(cvApplicationSectionList!=null){
        for(long i=1;i<=cvApplicationSectionList.size();i++){
            priorityList.add(i);
        }
        }
        modelAndView.setViewName("cv-template/cv-template-register");
//            modelAndView.addObject("cvApplicationSectionList",cvApplicationSectionList);
//            modelAndView.addObject("applicationFieldDictionaryItemsList",applicationFieldDictionaryItemsList);
        modelObjects.put("cvApplicationSectionList", cvApplicationSectionList);
//        modelObjects.put("cvApplicationSectionMap", cvApplicationSectionMap);
//        modelObjects.put("applicationFieldDictionaryItemsList", applicationFieldDictionaryItemsList);
        modelObjects.put("cvApplicationTemplate",new CvApplicationTemplate());
        modelObjects.put("priorityList",priorityList);
        modelAndView.addAllObjects(modelObjects);
//        }
//        else {
//            modelAndView.setViewName("error");
//        }
        return modelAndView;
    }


//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerNewCvTemplate(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,ModelAndView modelAndView){
//       if(!bindingResult.hasErrors()){
//           logger.info(" submitted form does not contain field errors ");
//        if(StringUtils.hasText(cvApplicationTemplate.getId()))
//        {
//            cvApplicationTemplateService.update(cvApplicationTemplate);
//            logger.info("updating the cv application template");
//        }
//        else
//        {
//            cvApplicationTemplateService.create(cvApplicationTemplate);
//            logger.info("creating new cv application template");
//        }
//       }
//       else{
//           logger.info(" submitted form contains field errors ");
//       }
//        modelAndView.setViewName("cv-template/cv-template-register");
////        return new RedirectView("registration_view");
//        return modelAndView;

        System.out.println(" size of cvApplicationSectionList ["+cvApplicationTemplate.getCvApplicationSectionList()+"]");
        System.out.println(" size of cvApplicationSectionList size ["+cvApplicationTemplate.getCvApplicationSectionList().size()+"]");

        modelAndView.setViewName("cv-template/cv-template-register");
        return modelAndView;
    }

}
