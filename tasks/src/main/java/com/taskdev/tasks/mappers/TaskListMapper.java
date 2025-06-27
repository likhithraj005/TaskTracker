package com.taskdev.tasks.mappers;

import com.taskdev.tasks.domain.dto.TaskListDTO;
import com.taskdev.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDTO(TaskListDTO taskListDTO);

    TaskListDTO toDTO(TaskList taskList);
}
