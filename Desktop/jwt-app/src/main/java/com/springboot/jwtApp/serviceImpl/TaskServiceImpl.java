package com.springboot.jwtApp.serviceImpl;

import com.springboot.jwtApp.dtos.TaskDTO;
import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.model.Task;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.repository.ProjectRepository;
import com.springboot.jwtApp.repository.TaskRepository;
import com.springboot.jwtApp.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserServiceImpl userService;
    @Override
    public Task addTask(Task task) {
        log.info("project id "+task.getProjectIdSample());
        Project project = this.projectRepository.findById(task.getProjectIdSample()).orElseThrow(()->new RuntimeException("could not find project"));
        task.setProject(project);
        return this.taskRepository.save(task);
    }
    //i need to set user task taken to null first ortherwise i cant delete because they're linked
    //and i cant cascade them, it will delete user record then, not efficient.
    @Override
    public Boolean deleteTask(Long taskID) {
        Task taskToDelete = taskRepository.findById(taskID).orElseThrow(()-> new RuntimeException("Cannot find task to delete"));;
        taskToDelete.getTakenBy().forEach(user->user.getTaskTaken().remove(taskToDelete));
        taskToDelete.setTakenBy(null);

        taskRepository.delete(taskToDelete);
        return true;
    }

    @Override
    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Collection<Task> getTaskByProject(Long projectID) {
        Project projectToFindWith= projectRepository.findById(projectID).orElseThrow(()->new RuntimeException("Cannot find project to get its tasks"));
        return taskRepository.findAllByProject(projectToFindWith);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Collection<TaskDTO> getMyTasks() {
        User user=this.userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Collection<Task> colTask= taskRepository.findAllByTakenBy(user);
        Collection<TaskDTO> colTaskDTO = new ArrayList<TaskDTO>();

        colTask.forEach(task -> {
            TaskDTO taskDTO=TaskDTO.builder()
                    .description(task.getDescription())
                    .id(task.getId())
                    .isAssigned(task.getIsAssigned())
                    .isDone(task.getIsDone())
                    .title(task.getTitle())
                    .build();
            colTaskDTO.add(taskDTO);
        });
        return colTaskDTO;
    }

    @Override
    public Boolean assignTaskToUser(Long taskId, String username) {
        User user=this.userService.getUser(username);
        Task task=this.getTaskById(taskId).orElse(null);
        if (task!=null){
            task.setIsAssigned(true);
            Collection<User> takenby=task.getTakenBy();
            if (takenby.contains(user)){
                return true;
            }
            takenby.add(user);
            Collection<Task> taskTaken=user.getTaskTaken();
            taskTaken.add(task);
            return true;
        }else{
            return false;
        }

    }
}
