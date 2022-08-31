package com.springboot.jwtApp.service;

import com.springboot.jwtApp.dtos.TaskDTO;
import com.springboot.jwtApp.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {
    Task addTask(Task task);
    Boolean deleteTask(Long taskID);
    Collection<Task> getTasks();
    Collection<Task> getTaskByProject(Long projectID);
    Optional<Task> getTaskById(Long taskId);
    Collection<TaskDTO> getMyTasks();
    Boolean assignTaskToUser(Long taskId, String username);

}
