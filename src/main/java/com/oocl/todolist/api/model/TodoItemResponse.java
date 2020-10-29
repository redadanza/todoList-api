package com.oocl.todolist.api.model;

public class TodoItemResponse {
    private Integer id;
    private String todoText;
    private boolean done;

    public TodoItemResponse() {

    }
    public TodoItemResponse(Integer id, String todoText, boolean done) {
        this.id = id;
        this.todoText = todoText;
        this.done = done;
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
