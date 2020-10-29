package com.oocl.todolist.api.integration;

import com.oocl.todolist.api.entity.TodoItem;
import com.oocl.todolist.api.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TodoIntegrationTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    private void tearDown() {
        todoRepository.deleteAll();
    }

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
        Integer todoId = todoRepository.save(todoItem).getId();
        mockMvc.perform(put("/todos/{Id}", todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoItemAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.todoText").value("todo item1"))
                .andExpect(jsonPath("$.done").value(true));

    }

    @Test
    public void should_delete_todoItem_when_perform_delete_given_todoItem_request() throws Exception {
        TodoItem todoItem = new TodoItem("todo item1", false);
        todoItem.setId(1);
        Integer todoId = todoRepository.save(todoItem).getId();

        List<TodoItem> todoItemList = todoRepository.findAll();
        mockMvc.perform(delete("/todos/{Id}", todoId))
                .andExpect(status().isOk());

    }


}
