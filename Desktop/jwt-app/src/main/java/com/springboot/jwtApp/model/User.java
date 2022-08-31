package com.springboot.jwtApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    private Long id;
    private String username;
    private String name;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles=new ArrayList<>();
    @ManyToMany
    private Collection<Task> taskTaken=new ArrayList<>();
    private String imageName;
    private String imageType;
    @Lob
    private byte[] image;
}
