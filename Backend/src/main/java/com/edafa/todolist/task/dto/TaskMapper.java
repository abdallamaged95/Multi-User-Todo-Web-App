package com.edafa.todolist.task.dto;

import com.edafa.todolist.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", source = "id.taskId")
    TaskResponseDTO entityToResponseDto(Task taskEntity);

    Task requestToEntity(TaskRequestDTO taskRequestDTO);

    @Mapping(target = "id.taskId", source = "id")
    @Mapping(target = "id.userId", source = "id")
    Task responseToEntity(TaskResponseDTO taskResponseDTO);

}
