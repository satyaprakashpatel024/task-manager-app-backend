package com.taskmanager.app.controller;

import com.taskmanager.app.dto.NoteRequestDTO;
import com.taskmanager.app.dto.NoteResponseDTO;

import com.taskmanager.app.entity.NoteEntity;

import com.taskmanager.app.services.NoteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task/{taskId}/notes")
public class NotesController {

    private NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        List<NoteEntity> notes = noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<NoteResponseDTO> addNoteToTask(
            @PathVariable("taskId") Integer taskId,
            @RequestBody NoteRequestDTO body){
        NoteEntity note = noteService.addNoteForTask(taskId,body.getTitle(),body.getBody());
        return ResponseEntity.accepted().body(new NoteResponseDTO(taskId, note));
    }
}
