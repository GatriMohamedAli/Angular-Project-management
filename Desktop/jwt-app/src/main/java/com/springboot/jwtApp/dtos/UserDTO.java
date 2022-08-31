package com.springboot.jwtApp.dtos;

import com.springboot.jwtApp.model.Role;
import com.springboot.jwtApp.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private Collection<Role> roles=new ArrayList<>();
    private Collection<TaskDTO> taskTaken=new ArrayList<>();
    private String imageName;
    private String imageType;
    private byte[] image;
}
