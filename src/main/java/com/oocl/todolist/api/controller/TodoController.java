package com.oocl.todolist.api.controller;

import com.oocl.todolist.api.mapper.TodoMapper;
import com.oocl.todolist.api.entity.TodoItem;
import com.oocl.todolist.api.dto.TodoItemRequest;
import com.oocl.todolist.api.dto.TodoItemResponse;
import com.oocl.todolist.api.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoMapper todoMapper, TodoService todoService) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping
    public List<TodoItemResponse> getAllTodos() {
        List<TodoItem> todoItems = todoService.getAll();

        return todoItems.stream().map(todoMapper::todoResponse).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItemResponse create(@RequestBody TodoItemRequest todoItemRequest) {
        TodoItem createdTodoItem = todoService.create(todoMapper.toEntity(todoItemRequest));
        return todoMapper.todoResponse(createdTodoItem);
    }

    @PutMapping("/{id}")
    public TodoItemResponse update(@PathVariable(required = true) Integer id) {

        return todoMapper.todoResponse(todoService.update(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        todoService.delete(id);
    }
}
