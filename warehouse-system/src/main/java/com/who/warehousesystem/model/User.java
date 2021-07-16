package com.who.warehousesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String name, String username, String password, Role role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.active = true;
        this.role = role;
    }
}
