package com.oocl.todolist.api.integration;

import com.oocl.todolist.api.model.TodoItem;
import com.oocl.todolist.api.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc

public class TodoIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_todos_when_getAll() throws Exception {
        //GIVEN
        TodoItem todoItem = new TodoItem("todo item1", false);
        todoItem.setId(1);
        todoRepository.save(todoItem);
        //WHEN THEN
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].todoText").value("todo item1"))
                .andExpect(jsonPath("$[0].done").isBoolean());

    }

}
