package com.springboot.jwtApp.service;

import com.springboot.jwtApp.dtos.ProjectDTO;
import com.springboot.jwtApp.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAllProjects();
    Project addProject(Project project);
    Project updateProject(Project project);
    Optional<ProjectDTO> getProjectById(Long projectID);
    boolean deleteProject(Long projectID);
}
