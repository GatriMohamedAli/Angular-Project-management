package com.springboot.jwtApp;

import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.model.Role;
import com.springboot.jwtApp.model.Task;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.serviceImpl.ProjectServiceImpl;
import com.springboot.jwtApp.serviceImpl.TaskServiceImpl;
import com.springboot.jwtApp.serviceImpl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

    @Bean
    CommandLineRunner run(final UserServiceImpl userService, final PasswordEncoder passwordEncoder, final ProjectServiceImpl projectService, final TaskServiceImpl taskService){
        return args->{
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            String hashedPass=passwordEncoder.encode("dalidali");
            userService.saveUser(new User(null,"Mohamed","mohamedali",hashedPass,new ArrayList<Role>(),null,null,null,null));
            userService.saveUser(new User(null,"Scarlette","Scarlette",hashedPass, new ArrayList<Role>(),null,null,null,null));
            userService.saveUser(new User(null,"Dualipa","Dualipa",hashedPass, new ArrayList<Role>(),null, null,null,null));
            userService.saveUser(new User(null,"Steve","Steve",hashedPass, new ArrayList<Role>(),null,null,null,null));

            userService.addRoleToUser("Mohamed","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("Mohamed","ROLE_ADMIN");
            userService.addRoleToUser("Steve","ROLE_ADMIN");
            userService.addRoleToUser("Mohamed","ROLE_MANAGER");
            userService.addRoleToUser("Dualipa","ROLE_USER");
            userService.addRoleToUser("Scarlette","ROLE_MANAGER");

            Project project= Project.builder()
                    .description("test")
                    .title("Ecommerce")
                    .build();
            Task task= Task.builder()
                    .description("test task")
                    .title("test task")
                    .projectIdSample(1L)
                    .build();
            projectService.addProject(project);
            taskService.addTask(task);

        };
    }
}
