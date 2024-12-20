package com.taskmanagement.taskapi.service;

import com.taskmanagement.taskapi.entity.Task;
import com.taskmanagement.taskapi.exception.DuplicateTitleException;
import com.taskmanagement.taskapi.exception.InvalidStatusException;
import com.taskmanagement.taskapi.exception.ResourceNotFoundException;
import com.taskmanagement.taskapi.mapper.TaskMapper;
import com.taskmanagement.taskapi.model.Status;
import com.taskmanagement.taskapi.model.TaskRequest;
import com.taskmanagement.taskapi.model.TaskResponse;
import com.taskmanagement.taskapi.repository.TaskRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public TaskResponse createTask(TaskRequest taskRequest){
        if(taskRepository.existsByTitle(taskRequest.getTitle())){
            throw new DuplicateTitleException("Task already exists:"+ taskRequest.getTitle());
        }
        Status status = validateStatus(taskRequest.getStatus());
        taskRequest.setStatus(String.valueOf(status));
        return taskMapper.convertEntityToResponse(taskRepository.save(taskMapper.convertRequestToEntity(taskRequest)));
    }
    @Override
    public TaskResponse retrieveTaskById(Long id){
        Task task= taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Task not found with ID:"+ id)
        );
        return taskMapper.convertEntityToResponse(task);
    }
    @Override
    public List<TaskResponse> retrieveAllTasks( ) {
        return taskRepository.findAll().stream().map(taskMapper::convertEntityToResponse).toList();
    }
    @Override
    public Task updateTask(Long id, TaskRequest updatedTaskRequest) {
        if(taskRepository.existsByTitle(updatedTaskRequest.getTitle())){
            throw new DuplicateTitleException("Task already exists with this title"+updatedTaskRequest.getTitle());
        }
        Task existingTask= taskRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Task not found")
        );
        existingTask.setTitle(updatedTaskRequest.getTitle());
        existingTask.setDescription(updatedTaskRequest.getDescription());
        existingTask.setStatus(validateStatus(updatedTaskRequest.getStatus()));
        return taskRepository.save(existingTask);
    }
    @Override
    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task Not Found");
        }
        taskRepository.deleteById(id);
    }
    @Override
    public String updateTaskStatus(Long id, String status) {
        Task task=taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task Not Found"));
        System.out.println(status);
        task.setStatus(validateStatus(status));
        System.out.println(task.getStatus());
        taskRepository.save(task);
        return "Status Updated Successfully to "+ task.getStatus();
    }

    private Status validateStatus(@NotNull(message = "status is required") String status) {
        try{
            return Status.valueOf(status.toUpperCase());
        }
        catch(IllegalArgumentException ex)
        {
            throw new InvalidStatusException("Invalid Status "+ status+ " accepted values are"+ Arrays.toString(Status.values()));
        }
    }
}
