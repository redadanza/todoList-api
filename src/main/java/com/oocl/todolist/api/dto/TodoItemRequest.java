package com.oocl.todolist.api.dto;

public class TodoItemRequest {
    private String todoText;
    private boolean done;


    public TodoItemRequest() {

    }

    public TodoItemRequest(String todoText, boolean done) {
        this.todoText = todoText;
        this.done = done;
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
