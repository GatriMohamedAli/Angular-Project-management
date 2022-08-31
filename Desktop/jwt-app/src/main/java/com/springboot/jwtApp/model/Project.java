package com.springboot.jwtApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Project {
    @Id
    @SequenceGenerator(
            name = "seq_id_project",
            sequenceName = "seq_id_project"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_id_project"
    )
    private Long id;
    private String title;
    private String description;
    @OneToMany( mappedBy = "project", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Collection<Task> taskCollection;

}
