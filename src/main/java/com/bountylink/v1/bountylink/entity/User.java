package com.bountylink.v1.bountylink.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length=45)
    private int id;

    private String username;

    private String email;

    private String password;

    private boolean flag = false;

    public User(String username, String email, String password, boolean flag) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.flag = flag;
    }
}
