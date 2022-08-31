package com.springboot.jwtApp.userdetails;

import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);
        if (user!=null){
            UserPrincipals userPrincipals=new UserPrincipals(user);
            return userPrincipals;
        }else{
            throw new UsernameNotFoundException("cant find this user in the database");
        }
    }
}
