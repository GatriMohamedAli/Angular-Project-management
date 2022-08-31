package com.springboot.jwtApp.repository;

import com.springboot.jwtApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
    @Query("select r.roleName from Role r")
    List<String> getRoleNames();
}
