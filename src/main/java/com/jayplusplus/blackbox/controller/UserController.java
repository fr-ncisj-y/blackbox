package com.jayplusplus.blackbox.controller;

import com.jayplusplus.blackbox.model.AuthRequest;
import com.jayplusplus.blackbox.model.GenericResponse;
import com.jayplusplus.blackbox.model.LoginResponse;
import com.jayplusplus.blackbox.entity.UserInfo;
import com.jayplusplus.blackbox.service.JwtService;
import com.jayplusplus.blackbox.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/v1/auth")
public class UserController {

    @Autowired
    private UserDetailsServiceImpl service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    /**
     * Welcome string.
     *
     * @return the string
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security";
    }

    /**
     * Add user generic response.
     *
     * @param userInfo the user info
     * @return the generic response
     */
    @PostMapping("/add-user")
    public GenericResponse addUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    /**
     * Login login response.
     *
     * @param authRequest the auth request
     * @return the login response
     */
    @PostMapping("/login")
    public LoginResponse login (@RequestBody AuthRequest authRequest){
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid User Name");
        }
    }

    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/getUsers")
    public List<UserInfo> getAllUsers(){
        return service.getAllUser();
    }

    /**
     * Get user user info.
     *
     * @param id the id
     * @return the user info
     */
    @GetMapping("/getUsers/{id}")
    public UserInfo getUser(@PathVariable Integer id){
        return service.getUser(id);
    }

}
