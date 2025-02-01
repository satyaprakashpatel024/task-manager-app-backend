package com.taskmanager.app.controller;


import com.taskmanager.app.dto.TaskRequestDTO;
import com.taskmanager.app.dto.TaskResponseDTO;
import com.taskmanager.app.entity.TaskEntity;
import com.taskmanager.app.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody TaskRequestDTO body){
        TaskEntity r = this.taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        TaskResponseDTO resp = new TaskResponseDTO();
        resp.setTitle(body.getTitle());
        resp.setDescription(body.getDescription());
        resp.setDeadline(body.getDeadline());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable int id){
        TaskEntity r = this.taskService.getTaskById(id);
        TaskResponseDTO resp = new TaskResponseDTO();
        resp.setTitle(r.getTitle());
        resp.setDescription(r.getDescription());
        resp.setDeadline(r.getDeadline());
        return ResponseEntity.ok(resp);
    }

}

















