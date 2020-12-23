package com.practice.doesthecatdie.controllers;

import com.practice.doesthecatdie.models.User;
import com.practice.doesthecatdie.security.*;
import com.practice.doesthecatdie.servises.MailSender;
import com.practice.doesthecatdie.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @Autowired
    private UserService userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MailSender mailSender;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        MyUserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        if(userDetails==null)
            userDetails=userDetailsService.loadUserByEmail(authenticationRequest.getUsername());
        if(userDetails==null)
            return new ResponseEntity<>("Incorrect username", HttpStatus.UNAUTHORIZED);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(),authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,userDetails.getId(),userDetails.getRole(),userDetails.getRatings()));
    }


    @PostMapping("/registerUser")
    public String registerUser(@RequestBody AuthorizationRequest request) {

        if(userDetailsService.loadUserByEmail(request.getEmail())!=null||userDetailsService.loadUserByUsername(request.getUsername())!=null)
            return "User with such username or email already exists";

        User user=userDetailsService.addNewUser(request.getUsername(),request.getEmail(),encoder.encode(request.getPassword()), "USER");

        try {
            mailSender.sendActivationLink(user.getEmail(),user.getId()+"");
        } catch (MessagingException e) {
            userDetailsService.deleteUser(user);
            return "Something went wrong with your email. Please,try to register again";
        }

        return "New user was successfully registered. Please, check your email to activate the account";
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin(@RequestBody AuthorizationRequest request){

        if(userDetailsService.loadUserByEmail(request.getEmail())!=null||userDetailsService.loadUserByUsername(request.getUsername())!=null)
            return "User with such userName or email already exists";

        User admin=userDetailsService.addNewUser(request.getUsername(),request.getEmail(),encoder.encode(request.getPassword()), "ADMIN");

        try {
            mailSender.sendActivationLink(admin.getEmail(),admin.getId()+"");
        } catch (MessagingException e) {
            userDetailsService.deleteUser(admin);
            return "Something went wrong with your email. Please,try to register again";
        }

        return "Successfully added new admin. Please, check new admin`s email to activate the account";
    }

    @GetMapping("/activateAccount")
    public String activate(String userId){
        User user=userDetailsService.loadUserById(Long.parseLong(userId));
        if(user==null)
            return "Couldn`t find a user to activate";
        userDetailsService.activateUser(user);
        return "Your account was successfully activated";
    }


}
