package com.taskmanager.app.controller;


import com.taskmanager.app.dto.ErrorResponseDTO;
import com.taskmanager.app.dto.TaskRequestDTO;
import com.taskmanager.app.dto.TaskResponseDTO;
import com.taskmanager.app.dto.TaskUpdateDTO;

import com.taskmanager.app.entity.NoteEntity;
import com.taskmanager.app.entity.TaskEntity;

import com.taskmanager.app.services.NoteService;
import com.taskmanager.app.services.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;
    private final NoteService noteService;
    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        List<TaskEntity> task = this.taskService.getAllTasks();
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskResponseDTO> addTask(@RequestBody TaskRequestDTO body) throws ParseException {
        TaskEntity r = this.taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        TaskResponseDTO resp = new TaskResponseDTO();
        resp.setId(r.getId());
        resp.setTitle(r.getTitle());
        resp.setDescription(r.getDescription());
        resp.setDeadline(r.getDeadline());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") int id){
        TaskEntity task = this.taskService.getTaskById(id);
        List<NoteEntity> list = noteService.getNotesForTask(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        TaskResponseDTO data = modelMapper.map(task,TaskResponseDTO.class);
        data.setNotes(list);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody TaskUpdateDTO body, @PathVariable int id) throws Exception {
        TaskEntity task = this.taskService.updateTask(id,body.getDescription(),body.getDeadline(),body.isCompleted());
        if(task==null){
            throw new Exception("invalid task id.");
        }
        TaskResponseDTO t = new TaskResponseDTO();
        t.setTitle(task.getTitle());
        t.setDeadline(task.getDeadline());
        t.setDescription(task.getDescription());
        t.setCompleted(task.isCompleted());
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

















