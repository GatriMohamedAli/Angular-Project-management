package com.springboot.jwtApp.controller;

import com.springboot.jwtApp.dtos.ProjectDTO;
import com.springboot.jwtApp.dtos.TaskDTO;
import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.model.Task;
import com.springboot.jwtApp.model.User;
import com.springboot.jwtApp.payloads.TaskAssignment;
import com.springboot.jwtApp.serviceImpl.ProjectServiceImpl;
import com.springboot.jwtApp.serviceImpl.TaskServiceImpl;
import com.springboot.jwtApp.serviceImpl.UserServiceImpl;
import com.springboot.jwtApp.userdetails.UserPrincipals;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@Slf4j
public class TaskController {
    private final TaskServiceImpl taskService;
    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Optional<ProjectDTO>> getByProjects(@RequestParam Long projectId){
        return ResponseEntity.ok(this.projectService.getProjectById(projectId));
    }
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        return ResponseEntity.ok(this.taskService.addTask(task));
    }
    @DeleteMapping("/{taskId}")
    public Boolean deleteTask(@PathVariable Long taskId){
        return this.taskService.deleteTask(taskId);
    }
    /* asssign task to the current logged in user, so basicly he chooses what he takes*/
    @GetMapping("/assign")
    public ResponseEntity<Task> TakeTask (@RequestParam Long taskId){
        User user=this.userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Task task=this.taskService.getTaskById(taskId).orElse(null);
        if (task!=null){
            Collection<User> takenby=task.getTakenBy();
            takenby.add(user);
            task.setTakenBy(takenby);
            Collection<Task> taskTaken=user.getTaskTaken();
            taskTaken.add(task);
            user.setTaskTaken(taskTaken);
            userService.saveUser(user);
            taskService.addTask(task);
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/my")
    public ResponseEntity<Collection<TaskDTO>> getMyTasks(){
        return ResponseEntity.ok(taskService.getMyTasks());
    }
    //admin does this
    @PostMapping("/assignto")
    public Boolean assignTaskToUser(@RequestBody TaskAssignment taskAssignment){
        return this.taskService.assignTaskToUser(taskAssignment.getTaskId(),taskAssignment.getUsername());
    }
}
