package com.taskmanager.app.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {
    private  int id;
    private String title;
    private String body;
}
