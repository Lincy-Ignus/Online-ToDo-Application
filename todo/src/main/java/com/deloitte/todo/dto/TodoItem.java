package com.deloitte.todo.dto;

import com.deloitte.todo.models.ToDo;


public class TodoItem {
    private int id;
    private String task;
    private Boolean done;
    private String lastUpdateTime;
    public TodoItem(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public TodoItem(ToDo entity){
        this.id = entity.getId();
        this.task = entity.getTask();
        this.done =entity.getDone();
        this.lastUpdateTime= entity.getLastUpdateTime();
    }
}
