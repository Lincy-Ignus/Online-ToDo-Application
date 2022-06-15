package com.deloitte.todo.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TODO_TBL")
public class ToDo {
    @Column
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String task;
    @Column
    private Boolean done;
    @Column
    private String lastUpdateTime;
    @ManyToOne
    private User user;

}
