package com.springboot.jwtApp.controller;

import lombok.Data;

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
