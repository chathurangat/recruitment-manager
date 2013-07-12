package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.model.Vacancy;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import com.hsenidmobile.recruitment.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/9/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/vacancy")
public class VacancyController {
    private Integer IMAGE_MAX_SIZE = 500000;

    private String uploadsDir = "/opt/tomcat/uploads";

    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;

    @Autowired
    private VacancyService vacancyService;


    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/vacancy_generate_view",method = RequestMethod.GET)
    public ModelAndView showVacancyAddGeneration(){
        List<CvApplicationTemplate> cvApplicationTemplate = cvApplicationTemplateService.findAllCvTemplate();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv template ["+cvApplicationTemplate+"]");
        if (cvApplicationTemplate!=null){
            modelAndView.setViewName("vacancy_add_generation/vacancy_add");
            modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/vacancy_generate",method = RequestMethod.POST)
    public ModelAndView vacancyAddGenerationInsert(@Valid Vacancy vacancy,BindingResult bindingResult,ModelAndView modelAndView)
    {
        modelAndView.setViewName("vacancy_add_generation/vacancy_add");
        CvApplicationTemplate  selectedCvApplicationTemplate=new CvApplicationTemplate();
        this.findSelectedCvApplicationTemplate(selectedCvApplicationTemplate);
        List<CvApplicationTemplate> cvApplicationTemplate = cvApplicationTemplateService.findAllCvTemplate();
        modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);

        //vacancy.getId(selectedCvApplicationTemplate);

        //image Upload -------------------------------------


     /*
       MultipartFile filea= vacancy.getAdImage();

        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (filea.getSize() > 0) {
            inputStream = filea.getInputStream();
            String fileName = uploadsDir + "/" + file.getOriginalFilename();


            outputStream = new FileOutputStream(fileName);
            int readBytes = 0;
            byte[] buffer = new byte[IMAGE_MAX_SIZE];
            while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
            outputStream.close();
            inputStream.close();
           // modelAndView.setViewName("vacancy_add_generation/vacancy_add_success");
            return modelAndView;

        }
        //----------------------------------------------------------      */
       //----------------------------------------------------------
        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(vacancy.getId())){
                vacancyService.update(vacancy);
                // logger.info("registering new cv template section form contains no errors (update) ");
                System.out.println(" there are no errors (update)");
            }
            else
            {
                vacancyService.create(vacancy);
                // logger.info("registering new cv template section form contains no errors (create) ");
                System.out.println(" there are no errors (create)");
            }
            return modelAndView;
        }
        else {

            // logger.info("registering new cv template section form contains errors ");
            modelAndView.setViewName("error");
            return modelAndView;
        }

      }


    private void findSelectedCvApplicationTemplate(CvApplicationTemplate selectedCvApplicationTemplate)
    {
        List<CvApplicationTemplate> cvApplicationTemplateList =  cvApplicationTemplateService.findAllCvTemplate();
        for (int index=0;index<cvApplicationTemplateList.size();index++)
        {
           CvApplicationTemplate cvApplicationTemplate=cvApplicationTemplateList.get(index);
            if(cvApplicationTemplate.getId()!=null)
            {
                CvApplicationTemplate cvApplicationTemplate1=cvApplicationTemplateService.findCvTemplateById(cvApplicationTemplate.getId());

                if(cvApplicationTemplate1!=null) {
                    selectedCvApplicationTemplate = cvApplicationTemplate1;
                }
            }

        }

    }






}






