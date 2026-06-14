package com.portfolio.taskmanager.service;


import com.portfolio.taskmanager.model.Task;
import com.portfolio.taskmanager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask(Long id,Task updatedTask){
        Task existingTask=taskRepository.findById(id).orElseThrow(()->new RuntimeException("User with this id not found"));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setCompleted(updatedTask.getCompleted());
        return taskRepository.save(existingTask);
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
