package com.springboot.jwtApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_id_seq",
            sequenceName = "role_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_seq"
    )
    private Long id;
    private String roleName;
}
