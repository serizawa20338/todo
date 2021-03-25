package com.example.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Todo {
    private int id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private boolean done;
}
