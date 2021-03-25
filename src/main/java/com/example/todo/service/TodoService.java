package com.example.todo.service;

import com.example.todo.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
    Todo findById(int id);
    int insert(Todo todo);
    int update(Todo todo);
    int delete(int id);
}
