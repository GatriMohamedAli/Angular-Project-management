package com.springboot.jwtApp.serviceImpl;

import com.springboot.jwtApp.dtos.ProjectDTO;
import com.springboot.jwtApp.dtos.TaskDTO;
import com.springboot.jwtApp.dtos.UserDTO;
import com.springboot.jwtApp.model.Role;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.repository.RoleRepository;
import com.springboot.jwtApp.repository.UserRepository;
import com.springboot.jwtApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        Collection<User> colUsers= userRepository.findAll();
        List<UserDTO> listUsersDTO= new ArrayList<>();
        List<TaskDTO> listTaskDTO= new ArrayList<>();
        colUsers.forEach(user -> {
            listTaskDTO.clear();
            user.getTaskTaken().forEach(task -> {
                ProjectDTO projectDTO=ProjectDTO.builder()
                        .description(task.getProject().getDescription())
                        .title(task.getProject().getTitle())
                        .id(task.getProject().getId())
                        .build();
                TaskDTO taskDTO=TaskDTO.builder()
                        .description(task.getDescription())
                        .id(task.getId())
                        .project(projectDTO)
                        .isAssigned(task.getIsAssigned())
                        .isDone(task.getIsDone())
                        .title(task.getTitle())
                        .build();
                listTaskDTO.add(taskDTO);
            });
            UserDTO userDTO=UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .roles(user.getRoles())
                    .username(user.getUsername())
                    .imageName(user.getImageName()!=null ? user.getImageName() : null)
                    .imageType(user.getImageType()!= null ? user.getImageType(): null)
                    .image(user.getImage()!=null ? user.getImage():null)
                    .taskTaken(listTaskDTO.stream().toList())
                    .build();
            listUsersDTO.add(userDTO);

        });
        return listUsersDTO;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info(username);
        log.info(roleName);
        User user= userRepository.findByUsername(username);
        Role role=roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }



    @Override
    public List<String> getRoles() {
        return this.roleRepository.getRoleNames();
    }

    @Override
    public List<String> getUsernames() {
        return this.userRepository.getUsernames();
    }

    @Override
    public Boolean changeProfilePicture(MultipartFile file) throws IOException {
        User user=userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setImageName(file.getOriginalFilename());
        user.setImageType(file.getContentType());
        user.setImage(file.getBytes());
        userRepository.save(user);
        return true;
    }

    @Override
    public Collection<Role> getUserRoles(String username) {
        return this.userRepository.findByUsername(username).getRoles();
    }

    @Override
    public Boolean revokeRole(String username, String rolename) {
        User user=this.userRepository.findByUsername(username);
        log.info(rolename);
        Role role=this.roleRepository.findByRoleName(rolename);
        return user.getRoles().remove(role);
    }
}

