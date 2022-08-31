package com.springboot.jwtApp.repository;

import com.springboot.jwtApp.model.Project;
import com.springboot.jwtApp.model.Task;
import com.springboot.jwtApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Collection<Task> findAllByProject(Project project);
    Collection<Task> findAllByTakenBy(User user);
}
