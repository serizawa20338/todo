package com.example.todo.controller;

import com.example.todo.form.TodoForm;
import com.example.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
public class TodoController {
    private static Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String index(Model model) {
        var todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public TodoForm findById(@PathVariable("id") int id) {
        var todo = todoService.findById(id);
        // FIXME
        var todoForm = new TodoForm(todo.getId(), todo.getTitle(), todo.getContent(),
                todo.getDueDate().format(DateTimeFormatter.ISO_DATE), todo.isDone());
        return todoForm;
    }

    @PostMapping("/api/create")
    @ResponseBody
    public ResponseEntity create(@Validated @RequestBody TodoForm todoForm) {
        logger.info("/create");
        logger.info(todoForm.getTitle());
        var todo = todoForm.convert();
        todoService.insert(todo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/update")
    @ResponseBody
    public ResponseEntity update(@Validated @RequestBody TodoForm todoForm) {
        var todo = todoForm.convert();
        todoService.update(todo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") int id) {
        todoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
