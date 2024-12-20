package com.taskmanagement.taskapi.controller;

import com.taskmanagement.taskapi.entity.Task;
import com.taskmanagement.taskapi.model.TaskRequest;
import com.taskmanagement.taskapi.model.TaskResponse;
import com.taskmanagement.taskapi.service.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskServiceImpl.createTask(taskRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> retrieveTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskServiceImpl.retrieveTaskById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> retrieveAllTasks() {
        return ResponseEntity.ok(taskServiceImpl.retrieveAllTasks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskServiceImpl.updateTask(id, taskRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long id,@Valid @RequestBody String status) {
        return ResponseEntity.ok(taskServiceImpl.updateTaskStatus(id, status));
    }


}
