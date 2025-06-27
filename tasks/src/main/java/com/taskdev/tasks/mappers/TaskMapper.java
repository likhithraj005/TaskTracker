package com.taskdev.tasks.mappers;

import com.taskdev.tasks.domain.dto.TaskDTO;
import com.taskdev.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDTO(TaskDTO taskDTO);

    TaskDTO toDTO(Task task);
}
