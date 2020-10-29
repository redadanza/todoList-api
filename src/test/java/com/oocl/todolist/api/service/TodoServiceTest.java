package com.oocl.todolist.api.service;

import com.oocl.todolist.api.model.TodoItem;
import com.oocl.todolist.api.repository.TodoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    TodoRepository repository = Mockito.mock(TodoRepository.class);
    TodoItem todoItem = new TodoItem("todo item1", false);

    List<TodoItem> expectedTodoItem = asList(todoItem);
    @Test
    public void should_return_todos_when_getAll_request(){
        //GIVEN
        when(repository.findAll()).thenReturn(expectedTodoItem);
        TodoService service = new TodoService(repository);
        //WHEN
        List<TodoItem> actual = service.getAll();
        //THEN
        Assertions.assertEquals(1, actual.size());
    }
    @Test
    public void should_create_todoItem_when_create_given_todoItem_request(){
        when(repository.save(todoItem)).thenReturn(todoItem);
        TodoService employeeService = new TodoService(repository);
        todoItem.setId(1);
        //WHEN
        TodoItem actual = employeeService.create(todoItem);
        //THEN
        Assertions.assertEquals(1, actual.getId());
    }
}
