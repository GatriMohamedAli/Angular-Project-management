package com.springboot.jwtApp.controller;

import com.google.gson.Gson;
import com.springboot.jwtApp.jwtstuff.JwtUtils;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.payloads.LoginRequest;
import com.springboot.jwtApp.repository.UserRepository;
import com.springboot.jwtApp.serviceImpl.UserServiceImpl;
import com.springboot.jwtApp.userdetails.UserPrincipals;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthentificationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserServiceImpl userService;
    private final static Gson gson=new Gson();

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> authenticateUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipals userPrincipal= (UserPrincipals) authentication.getPrincipal();
        String jwt=jwtUtils.generateJwtToken(userPrincipal);
        Map<String,String> map=new HashMap<>();
        map.put("Token",jwt);
        //return ResponseEntity.ok(gson.toJson(jwt));
        return map;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }else{
            userService.addRoleToUser(user.getUsername(),"ROLE_ADMIN");
            URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/signup").toUriString());
            return ResponseEntity.created(uri).body(userRepository.save(user));
        }
    }

}
