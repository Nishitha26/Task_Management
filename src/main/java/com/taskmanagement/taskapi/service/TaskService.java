package com.taskmanagement.taskapi.service;

import com.taskmanagement.taskapi.entity.Task;
import com.taskmanagement.taskapi.model.TaskRequest;
import com.taskmanagement.taskapi.model.TaskResponse;

import java.util.List;

public interface TaskService {
    public TaskResponse createTask(TaskRequest task);
    public TaskResponse retrieveTaskById(Long id);
    public List<TaskResponse> retrieveAllTasks();
    public Task updateTask(Long id, TaskRequest task);
    public void deleteTask(Long id);
    public String updateTaskStatus(Long id, String status);
}
