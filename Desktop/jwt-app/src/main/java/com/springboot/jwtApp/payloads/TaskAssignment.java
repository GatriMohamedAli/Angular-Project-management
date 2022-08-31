package com.springboot.jwtApp.payloads;

import lombok.Data;

@Data
public class TaskAssignment {
    private Long taskId;
    private String username;
}
