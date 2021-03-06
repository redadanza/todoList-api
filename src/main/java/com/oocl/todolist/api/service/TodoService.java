package com.oocl.todolist.api.service;

import com.oocl.todolist.api.entity.TodoItem;
import com.oocl.todolist.api.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    public final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> getAll() {
        return repository.findAll();
    }

    public TodoItem create(TodoItem todoItem) {
        todoItem.setDone(false);
        return repository.save(todoItem);
    }

    public TodoItem update(Integer id) {
        TodoItem todoItem = repository.findById(id).get();
        todoItem.setDone(!todoItem.isDone());
        return repository.save(todoItem);
    }

    public void delete(Integer todoId) {
        repository.deleteById(todoId);
    }
}
