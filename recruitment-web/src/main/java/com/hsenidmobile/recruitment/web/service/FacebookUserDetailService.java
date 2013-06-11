package com.hsenidmobile.recruitment.web.service;

import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     user detail service dedicated to work with facebook user authentication
 * </p>
 */
public class FacebookUserDetailService implements UserDetailsService{

    @Autowired
    private ApplicantService applicantService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        System.out.println(" == authenticating user == ["+username+"]");
        List<GrantedAuthority> grantedAuthorityList =  new ArrayList<GrantedAuthority>();

        Applicant applicant = applicantService.findApplicantFromSocialNetworkDetails("facebook",username,true);

        if(applicant!=null){
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
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
