package com.hsenidmobile.recruitment.web.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        System.out.println(" == authenticating user == ["+username+"]");
        List<GrantedAuthority> grantedAuthorityList =  new ArrayList<GrantedAuthority>();

        List<SimpleGrantedAuthority> GrantedAuthorityListForCvTemplateCreation = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_CREATE_CV_SECTION"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_TEMPLATE"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_FIELD"));

        List<SimpleGrantedAuthority> GrantedAuthorityListForCvFieldCreation = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_CREATE_CV_TEXTBOX"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_TEXTAREA"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_DROPDOWN"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_DROPDOWN_OPTIONS"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_CHECKBOX"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_CHECKBOX_VALUES"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_RADIOBUTTON"),
                new SimpleGrantedAuthority("ROLE_CREATE_CV_RADIOBUTTON_VALUES"));

        List<SimpleGrantedAuthority> GrantedAuthorityListForCvApplicant = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_VIEW_CANDIDATE_LIST"));

        List<SimpleGrantedAuthority> GrantedAuthorityListForVacancyCreation = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_CREATE_VACANCY"));

//        Applicant applicant = applicantService.findApplicantFromSocialNetworkDetails("google",username,true);

//        if(applicant!=null){
//            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
//            UserDetails user = new User(username,"21232f297a57a5a743894a0e4a801fc3", true, true, true, true,grantedAuthorityList);
//            System.out.println(" user authentication success");
//            return user;
//        }
//        else {
//            System.out.println(" user authentication failed ");
//            return new User(username, "DEFAULT_PW", true, true, true, true,grantedAuthorityList);
//        }

        String emailDomain = username.split("@")[1];
        System.out.println(" email domain is ["+emailDomain+"]");

        if(emailDomain!=null && (emailDomain.equals("hsenidmobile.com") || emailDomain.equals("hsenid.lk") || emailDomain.equals("hsenid.com"))){
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
          //  if(username.equalsIgnoreCase("nilaxan@hsenidmobile.com"))
          //  {
                grantedAuthorityList.addAll(GrantedAuthorityListForCvApplicant);
                grantedAuthorityList.addAll(GrantedAuthorityListForCvFieldCreation);
                grantedAuthorityList.addAll(GrantedAuthorityListForCvTemplateCreation);
                grantedAuthorityList.addAll(GrantedAuthorityListForVacancyCreation);
          //  }
            UserDetails user = new User(username,"21232f297a57a5a743894a0e4a801fc3", true, true, true, true,grantedAuthorityList);
                System.out.println(" user authentication success");
                return user;

        }
        else {
            System.out.println(" user authentication failed ");
            return new User(username, "DEFAULT_PW", true, true, true, true,grantedAuthorityList);
        }
    }
}
