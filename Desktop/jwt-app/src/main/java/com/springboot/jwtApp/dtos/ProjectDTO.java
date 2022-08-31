package com.springboot.jwtApp.dtos;

import com.springboot.jwtApp.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDTO {

    private Long id;
    private String title;
    private String description;
    private Collection<TaskDTO> taskCollection;
}
