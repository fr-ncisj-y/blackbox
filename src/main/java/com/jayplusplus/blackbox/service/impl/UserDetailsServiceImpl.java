package com.jayplusplus.blackbox.service.impl;

import com.jayplusplus.blackbox.model.GenericResponse;
import com.jayplusplus.blackbox.entity.UserInfo;
import com.jayplusplus.blackbox.repository.UserInfoRepository;
import com.jayplusplus.blackbox.service.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        return userInfo.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("Username not Found: " + username));
    }

    /**
     * Add user generic response.
     *
     * @param userinfo the userinfo
     * @return the generic response
     */
    public GenericResponse addUser(UserInfo userinfo){
        GenericResponse response = new GenericResponse();
        userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
        UserInfo info = userInfoRepository.save(userinfo);
        if(info.getId() != null){
            response.setMessage("OK");
            response.setCode(00);
        }else{
            response.setMessage("Failed to saved user");
            response.setCode(100);
        }

        return response;
    }

    /**
     * Get all user list.
     *
     * @return the list
     */
    public List<UserInfo> getAllUser(){
        return userInfoRepository.findAll();
    }

    /**
     * Get user user info.
     *
     * @param id the id
     * @return the user info
     */
    public UserInfo getUser(Integer id){
        return userInfoRepository.findById(id).get();
    }
}
