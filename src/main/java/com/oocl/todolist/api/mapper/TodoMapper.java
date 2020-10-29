package com.oocl.todolist.api.mapper;

import com.oocl.todolist.api.entity.TodoItem;
import com.oocl.todolist.api.dto.TodoItemRequest;
import com.oocl.todolist.api.dto.TodoItemResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoItemResponse todoResponse(TodoItem todoItem) {
        TodoItemResponse response = new TodoItemResponse();
        BeanUtils.copyProperties(todoItem, response);
        return response;
    }

    public TodoItem toEntity(TodoItemRequest todoItemRequest) {
        TodoItem todoItem = new TodoItem();
        BeanUtils.copyProperties(todoItemRequest, todoItem);
        return todoItem;
    }
}
