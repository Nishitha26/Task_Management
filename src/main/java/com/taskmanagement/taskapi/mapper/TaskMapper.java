package com.taskmanagement.taskapi.mapper;

import com.taskmanagement.taskapi.entity.Task;
import com.taskmanagement.taskapi.model.Status;
import com.taskmanagement.taskapi.model.TaskRequest;
import com.taskmanagement.taskapi.model.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task convertRequestToEntity(TaskRequest taskRequest){
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(Status.valueOf(taskRequest.getStatus()));
        return task;
    }

    public TaskResponse convertEntityToResponse(Task task){
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus().toString());
        taskResponse.setCreateTime(task.getCreateTime());
        taskResponse.setUpdateTime(task.getUpdateTime());
        return taskResponse;
    }
}
