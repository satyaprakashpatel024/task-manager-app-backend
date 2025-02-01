package com.taskmanager.app.controller;


import com.taskmanager.app.dto.ErrorResponseDTO;
import com.taskmanager.app.dto.TaskRequestDTO;
import com.taskmanager.app.dto.TaskResponseDTO;
import com.taskmanager.app.dto.TaskUpdateDTO;
import com.taskmanager.app.entity.TaskEntity;
import com.taskmanager.app.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        return ResponseEntity.ok(this.taskService.getAllTasks());
    }

    @PostMapping("")
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody TaskRequestDTO body) throws ParseException {
        TaskEntity r = this.taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        TaskResponseDTO resp = new TaskResponseDTO();
        resp.setTitle(r.getTitle());
        resp.setDescription(r.getDescription());
        resp.setDeadline(r.getDeadline());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable int id){
        TaskEntity r = this.taskService.getTaskById(id);
        TaskResponseDTO resp = new TaskResponseDTO();
        resp.setTitle(r.getTitle());
        resp.setDescription(r.getDescription());
        resp.setDeadline(r.getDeadline());
        resp.setCompleted(r.getCompleted());
        return ResponseEntity.ok(resp);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody TaskUpdateDTO body, @PathVariable int id) throws Exception {
        TaskEntity task = this.taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.getCompleted());
        if(task==null){
            throw new Exception("invalid task id.");
        }
        TaskResponseDTO t = new TaskResponseDTO();
        t.setTitle(task.getTitle());
        t.setDeadline(task.getDeadline());
        t.setDescription(task.getDescription());
        t.setCompleted(task.getCompleted());
        return ResponseEntity.accepted().body(t);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleParseError(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format.."));
        }
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }


}

















