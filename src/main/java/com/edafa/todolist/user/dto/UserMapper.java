package com.edafa.todolist.user.dto;

import com.edafa.todolist.user.entity.Role;
import com.edafa.todolist.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequestDTO userRequestDTO);

    UserRequestDTO entityToRequestDTO(User user);

    UserResponseDTO entityToResponseDTO(User user);
}
