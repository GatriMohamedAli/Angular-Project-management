package com.springboot.jwtApp.controller;

import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
 
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getProjects(){
        return this.projectService.getAllProjects();
    }

    @PostMapping
    public Project addProject(@RequestBody Project project){
        return this.projectService.addProject(project);
    }

    @DeleteMapping("/{projectID}")
    public Boolean deleteProject(@PathVariable Long projectID){
        return this.projectService.deleteProject(projectID);
    }
}
