package com.springboot.jwtApp.controller;

import com.springboot.jwtApp.dtos.UserDTO;
import com.springboot.jwtApp.model.Role;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.repository.UserRepository;
import com.springboot.jwtApp.serviceImpl.UserServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j

@RequestMapping("/api/v1")
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users").toUriString());
        return ResponseEntity.created(uri).header("access-control-allow-header","*").body(userService.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/grant")
    public ResponseEntity<?> grantUserRole(@RequestBody RoleToUserForm roleToUserForm){
        log.info(roleToUserForm.getUsername());
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users").toUriString());
        userService.addRoleToUser(roleToUserForm.getUsername(),roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/roles")
    public List<String> getRoles(){
        return userService.getRoles();
    }
    @GetMapping("/usernames")
    public List<String> getUsernames(){
        return this.userService.getUsernames();
    }

    @PostMapping("/user/picture")
    public Boolean changeProfilePicture(@RequestBody MultipartFile picture) throws IOException {
        return this.userService.changeProfilePicture(picture);
    }
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id){
        User user=userRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getImageName() + "\"")
                .header("Content-Type", "image/jpeg")
                .body(user.getImage());
    }
    @GetMapping("/user/roles")
    public ResponseEntity<Collection<Role>> getUserRole(@RequestParam String username){
        return ResponseEntity.ok(this.userService.getUserRoles(username));
    }

    @PostMapping("/role/revoke")
    public ResponseEntity<Boolean> revokeRole(@RequestBody RoleToUserForm roleToUserForm){
        log.error(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok(this.userService.revokeRole(roleToUserForm.getUsername(),roleToUserForm.getRoleName()));
    }

}
