package com.springboot.jwtApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Task  {
    @SequenceGenerator(
            name = "seq_id_task",
            sequenceName = "seq_id_task"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_id_task"
    )
    @Id
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    @ToString.Exclude
    private Project project;
    private Boolean isDone;
    private Boolean isAssigned;
    private Long projectIdSample;
    @ManyToMany
    @ToString.Exclude
    private Collection<User> takenBy;
}
