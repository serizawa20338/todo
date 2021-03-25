package com.example.todo.form;

import com.example.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TodoForm {
    private int id;

    @NotNull
    @Size(min=1, max=100)
    private String title;

    @Size(min=0, max=1000)
    private String content;

    @NotNull
    private String dueDate;

    @NotNull
    private boolean done;

    public Todo convert() {
        return new Todo(id, title, content, LocalDate.parse(dueDate), done);
    }
}
