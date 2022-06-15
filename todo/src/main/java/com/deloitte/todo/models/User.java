package com.deloitte.todo.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL")
public class User {
    @Column
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
