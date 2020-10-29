package com.oocl.todolist.api.entity;

import javax.persistence.*;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_generator")
    @SequenceGenerator(name = "todo_generator")
    private Integer id;
    private String todoText;
    private boolean done;

    public TodoItem(String todoText, boolean done) {
        this.todoText = todoText;
        this.done = done;
    }

    public TodoItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
