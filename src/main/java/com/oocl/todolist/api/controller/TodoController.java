package com.oocl.todolist.api.controller;

import com.oocl.todolist.api.model.TodoItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @GetMapping
    public List<TodoItem> getAllTodos() {
        return null;
    }
}
