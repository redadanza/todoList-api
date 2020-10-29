package com.oocl.todolist.api.service;

import com.oocl.todolist.api.model.TodoItem;
import com.oocl.todolist.api.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    public final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> getAll(){
        return repository.findAll();
    }
    public TodoItem create(TodoItem todoItem){
        todoItem.setDone(false);
        return repository.save(todoItem);
    }
}
