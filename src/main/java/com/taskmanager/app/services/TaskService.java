package com.taskmanager.app.services;

import com.taskmanager.app.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
    private int taskId = 1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title,String description,String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(this.taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(dateFormat.parse(deadline)); // TODO: validate date format
        task.setCompleted(false);
        this.taskId++;
        this.tasks.add(task);
        return task;
    }

    public List<TaskEntity> getAllTasks(){
        return this.tasks;
    }

    public TaskEntity getTaskById(Integer id){
        try{
            return this.tasks.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElse(null);
        }catch(Exception e){
            System.out.println("error in task service");
        }
        return null;
    }

    public TaskEntity updateTask(int id,String description,String deadline,boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);
        if(task == null){
            return null;
        }
        if(description != null) {
            task.setDescription(description);
        }
        if(deadline!=null) {
            task.setDeadline(dateFormat.parse(deadline));
        }
        if (isValidCompletedStatus(completed)) {
            task.setCompleted(completed);
        } else {
            return null;
        }
        return task;
    }

    private boolean isValidCompletedStatus(boolean completed) {
        return completed == true || completed == false;
    }

    public void deleteTaskById(int id){

    }
}


























