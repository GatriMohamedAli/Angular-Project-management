package com.springboot.jwtApp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private ProjectDTO project;
    private Boolean isDone;
    private Boolean isAssigned;
    private Long projectIdSample;
    private Collection<UserDTO> takenBy;
}
