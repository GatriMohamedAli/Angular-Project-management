package com.springboot.jwtApp.serviceImpl;

import com.springboot.jwtApp.dtos.ProjectDTO;
import com.springboot.jwtApp.dtos.TaskDTO;
import com.springboot.jwtApp.dtos.UserDTO;
import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.model.Task;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.repository.ProjectRepository;
import com.springboot.jwtApp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<ProjectDTO> getProjectById(Long projectID) {
        Collection<UserDTO> userDTOS=new ArrayList<>();
        Collection<TaskDTO> taskDTOS=new ArrayList<>();
        //Collection<UserDTO> userDTOS=new ArrayList<>();
        Project project= projectRepository.findById(projectID).get();
        project.getTaskCollection().forEach(task -> {
            task.getTakenBy().forEach(user -> {
                UserDTO userDTO= UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .image(user.getImage())
                        .imageType(user.getImageType())
                        .imageName(user.getImageName())
                        .roles(user.getRoles())
                        .name(user.getName())
                        .build();
                userDTOS.add(userDTO);
            });
            TaskDTO taskDTO=TaskDTO.builder()
                    .title(task.getTitle())
                    .isDone(task.getIsDone())
                    .isAssigned(task.getIsAssigned())
                    .description(task.getDescription())
                    .id(task.getId())
                    .projectIdSample(task.getProjectIdSample())
                    .takenBy(userDTOS.stream().toList())
                    .build();
            taskDTOS.add(taskDTO);
            userDTOS.clear();
        });
        ProjectDTO projectDTO=ProjectDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .taskCollection(taskDTOS)
                .build();
        return Optional.of(projectDTO);
    }

    @Override
    public boolean deleteProject(Long projectID) {
        Project project=projectRepository.findById(projectID).orElseThrow(()-> new RuntimeException("could not find the project"));
        Collection<Task> tasksToRemove=new ArrayList<>();
        Collection<User> usersToRemove=new ArrayList<>();
        Collection<Task> colTasks=project.getTaskCollection();
        colTasks.forEach(task -> {
            Collection<User> colUser=task.getTakenBy();
            tasksToRemove.add(task);
            colUser.forEach(user -> {
                log.info("test if task exists "+user.getTaskTaken().contains(task));
                usersToRemove.add(user);
                user.getTaskTaken().removeAll(tasksToRemove);
            });
            task.getTakenBy().removeAll(usersToRemove);
        });
        projectRepository.delete(project);
        return true;

    }
}
