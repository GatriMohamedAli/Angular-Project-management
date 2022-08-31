package com.springboot.jwtApp.service;

import com.springboot.jwtApp.dtos.UserDTO;
import com.springboot.jwtApp.model.Role;
import com.springboot.jwtApp.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<UserDTO> getAllUsers();
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<String> getRoles();
    List<String> getUsernames();
    Boolean changeProfilePicture(MultipartFile file) throws IOException;
    Collection<Role> getUserRoles(String username);
    Boolean revokeRole(String username, String rolename);
}
