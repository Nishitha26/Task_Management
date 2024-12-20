package com.taskmanagement.taskapi.repository;



import com.taskmanagement.taskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    boolean existsByTitle(String title);
}