package com.oocl.todolist.api.repository;

import com.oocl.todolist.api.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem,Integer> {

}
