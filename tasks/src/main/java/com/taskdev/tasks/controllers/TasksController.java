package com.taskdev.tasks.controllers;

import com.taskdev.tasks.domain.dto.TaskDTO;
import com.taskdev.tasks.domain.entities.Task;
import com.taskdev.tasks.mappers.TaskMapper;
import com.taskdev.tasks.services.TaskService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TasksController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDTO> listTasks(@PathVariable("task_list_id") UUID taskListId){
        return taskService.listTasks(taskListId).stream().map(taskMapper::toDTO).toList();
    }

    @PostMapping
    public TaskDTO createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDTO taskDTO){
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDTO(taskDTO));
        return taskMapper.toDTO(createdTask);
    }

//    @GetMapping(path = "/{task_id}")
//    public Optional<TaskDTO> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId){
//        return taskService.getTask(taskListId, taskId).map(taskMapper::toDTO);
//    }

    @GetMapping(path = "/{task_id}")
    public TaskDTO getTask(@PathVariable("task_list_id") UUID taskListId,
                           @PathVariable("task_id") UUID taskId) {
        return taskService.getTask(taskListId, taskId)
                .map(taskMapper::toDTO)
                .orElseThrow(() ->
                        new IllegalArgumentException("Task not found with ID: " + taskId + " in TaskList: " + taskListId));
    }

    @PutMapping(path = "/{task_id}")
    public TaskDTO updateTask(@PathVariable("task_list_id") UUID taskListId,
                              @PathVariable("task_id") UUID taskId,
                              @RequestBody TaskDTO taskDto){
        Task updatedTask = taskService.updateTask(taskListId, taskId, taskMapper.fromDTO(taskDto));
        return taskMapper.toDTO(updatedTask);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId,
                           @PathVariable("task_id") UUID taskId){
        taskService.deleteTask(taskListId, taskId);
    }
}
