package com.oocl.todolist.api.model;

public class TodoItem {
    int id;
    String todoText;
    boolean done;

    public TodoItem(String todoText, boolean done) {
        this.todoText = todoText;
        this.done = done;
    }
    public TodoItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
