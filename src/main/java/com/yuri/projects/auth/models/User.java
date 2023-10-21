package com.yuri.projects.auth.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
