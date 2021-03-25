package com.example.todo.service.impl;

import com.example.todo.entity.Todo;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoMapper todoMapper;

    @Override
    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    @Override
    public Todo findById(int id) {
        return todoMapper.findById(id);
    }

    @Override
    public int insert(Todo todo) {
        return todoMapper.insert(todo);
    }

    @Override
    public int update(Todo todo) {
        return todoMapper.update(todo);
    }

    @Override
    public int delete(int id) {
        return todoMapper.delete(id);
    }
}
