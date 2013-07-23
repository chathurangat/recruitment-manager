package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.model.Vacancy;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import com.hsenidmobile.recruitment.service.VacancyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/vacancy")
public class VacancyController {

    private static final Logger logger = LoggerFactory.getLogger(VacancyController.class);

    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;

    @Autowired
    private VacancyService vacancyService;


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/vacancy_generate_view",method = RequestMethod.GET)
    public ModelAndView showVacancyAddGeneration(){
        List<CvApplicationTemplate> cvApplicationTemplate = cvApplicationTemplateService.findAllCvTemplate();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv template ["+cvApplicationTemplate+"]");
        if (cvApplicationTemplate!=null){
            modelAndView.setViewName("vacancy_add_generation/vacancy_add");
            modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
            modelAndView.addObject("vacancy",new Vacancy());
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/vacancy_generate",method = RequestMethod.POST)
    public ModelAndView vacancyAddGenerationInsert(Vacancy vacancy,BindingResult bindingResult,ModelAndView modelAndView,HttpServletRequest request,HttpSession session)
    {
      System.out.println("vacancy cv template id"+vacancy.getCvApplicationTemplateId());
      if(!bindingResult.hasErrors()){

        MultipartFile filea1 = vacancy.getFileData();
        System.out.println("vacancy.getFileData()"+vacancy.getFileData());
        System.out.println("filea.getSize()"+filea1.getSize());
        try {

                MultipartFile filea = vacancy.getFileData();
               System.out.print("vacancy.getFileData()"+vacancy.getFileData());

                String fileName = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                System.out.print("filea.getSize()"+filea.getSize());
                if (filea.getSize() > 0) {
                    inputStream = filea.getInputStream();
                    fileName = request.getRealPath("") + "/resources/img/"
                            + filea.getOriginalFilename();

                    outputStream = new FileOutputStream(fileName);
                    vacancy.setFilename(filea.getOriginalFilename());
                    System.out.println("================Starting to get Image================");
                    System.out.println(filea.getOriginalFilename());
                    System.out.println("=====================================================");
                    int readBytes ;
                    byte[] buffer = new byte[8192];
                    while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
                        System.out.println("================Image in saving now================");
                        outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
                    session.setAttribute("uploadFile", "../resources/img/"
                            + filea.getOriginalFilename());
                }
            } catch (Exception e) {
                e.printStackTrace();
                 }
               System.out.print("End of file uploading statements");
               vacancy.setFileData(null);

          if(StringUtils.hasText(vacancy.getId())){
              System.out.print("Before update the vacancy");
              vacancyService.update(vacancy);
              System.out.print("After update the vacancy");
                // logger.info("registering new cv template section form contains no errors (update) ");
                System.out.println(" there are no errors (update)");
            }
            else
            {
                vacancyService.create(vacancy);
                // logger.info("registering new cv template section form contains no errors (create) ");
                System.out.println(" there are no errors (create)");
            }

            modelAndView.setViewName("vacancy_add_generation/vacancy_add_generation_success");
            return modelAndView;
        }
        else {

            // logger.info("registering new cv template section form contains errors ");
            modelAndView.setViewName("error");
             List<CvApplicationTemplate> cvApplicationTemplate = cvApplicationTemplateService.findAllCvTemplate();
             modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
            return modelAndView;
        }

      }



    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/vacancy-save-success")
    public ModelAndView displayVacancySaveSuccessPage(){
        logger.info(" displaying the vacancy success page after vacancy saved successfully");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("application-save-success");
        return modelAndView;
    }

}






