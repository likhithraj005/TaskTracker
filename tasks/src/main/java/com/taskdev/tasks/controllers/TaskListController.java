package com.taskdev.tasks.controllers;

import com.taskdev.tasks.domain.dto.TaskListDTO;
import com.taskdev.tasks.domain.entities.TaskList;
import com.taskdev.tasks.mappers.TaskListMapper;
import com.taskdev.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDTO> listTaskLists(){
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDTO)
                .toList();
    }

    @PostMapping
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO){
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDTO(taskListDTO)
        );
        return taskListMapper.toDTO(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public TaskListDTO getTaskList(@PathVariable("task_list_id") UUID taskListId) {
        return taskListService.getTaskList(taskListId)
                .map(taskListMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found with ID: " + taskListId));
    }

    @PutMapping("/{taskListId}")
    public TaskListDTO updateTaskList(@PathVariable UUID taskListId, @RequestBody TaskListDTO taskListDTO){
        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDTO(taskListDTO)
        );

        return taskListMapper.toDTO(updatedTaskList);
    }

    @DeleteMapping("/{taskListId}")
    public void deleteTaskList(@PathVariable UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }
}
