package com.pixelcatsoftware.auth.service;

import com.pixelcatsoftware.auth.details.UsersDetails;
import com.pixelcatsoftware.auth.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailsService implements UserDetailsService {

    private UserRepository userRepo;
    public LoginDetailsService(UserRepository injectedUserRepository) {
        userRepo = injectedUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserDetails usersDetails = new UsersDetails(this.userRepo.findUserByUsername(username));
        return usersDetails;
    }
    
}
