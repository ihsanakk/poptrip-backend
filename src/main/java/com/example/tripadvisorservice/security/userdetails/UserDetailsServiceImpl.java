package com.example.tripadvisorservice.security.userdetails;

import com.example.tripadvisorservice.entity.User;
import com.example.tripadvisorservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =  userService.findUserByUserName(s).orElseThrow(()->new UsernameNotFoundException("Username not found: "+s));
        return new UserDetailsImpl(user);
    }
}
