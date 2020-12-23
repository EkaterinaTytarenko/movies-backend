package com.practice.doesthecatdie.servises;

import com.practice.doesthecatdie.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    UserService userService;

    @Autowired
    MailSender mailSender;

    public void makeUserRequest(String userId,String request_text) throws Exception{
        User user=userService.loadUserById(Long.parseLong(userId));
        List<User> admins=userService.findAllAdmins();

        String email_body="<p>"+"User: "+user.getUsername()+"</p>"+"<p>"+"Email: "+user.getEmail()+"</p>"+"<p>"+"Request: "+request_text+"</p>";

        for(User admin:admins){
            mailSender.sendUserRequest(admin.getEmail(),email_body);
        }
    }
}
