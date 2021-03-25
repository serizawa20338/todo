package com.example.todo.mapper;

import com.example.todo.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<Todo> findAll();
    Todo findById(int id);
    int insert(Todo todo);
    int update(Todo todo);
    int delete(int id);
}
