package com.oocl.todolist.api.integration;

import com.oocl.todolist.api.model.TodoItem;
import com.oocl.todolist.api.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
    @Test
    public void should_create_todoItem_when_perform_post_given_todoItem_request() throws Exception {
        String todoItemAsJson = " {\n" +
                "            \"todoText\": \"todoText\",\n" +
                "            \"done\": false\n" +
                "            }";

        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoItemAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.todoText").value("todoText"))
                .andExpect(jsonPath("$.done").isBoolean());

    }
    @Test
    public void should_update_todoItem_when_perform_put_given_todoItem_request() throws Exception {
        TodoItem todoItem = new TodoItem("todo item1", false);
        todoItem.setId(1);
        todoRepository.save(todoItem);

        String todoItemAsJson = " {\n" +
                "            \"todoText\": \"todo item1\",\n" +
                "            \"done\": true\n" +
                "            }";

        mockMvc.perform(put("/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoItemAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.todoText").value("todo item1"))
                .andExpect(jsonPath("$.done").value(true));

    }


}
