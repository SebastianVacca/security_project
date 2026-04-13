package com.devcode.svacca.security_project.mapper.users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devcode.svacca.security_project.model.roles.entity.Role;
import com.devcode.svacca.security_project.model.users.dto.CreateUser;
import com.devcode.svacca.security_project.model.users.dto.ResponseUser;
import com.devcode.svacca.security_project.model.users.dto.UpdateUser;
import com.devcode.svacca.security_project.model.users.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "hireDate", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(CreateUser createUser);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "hireDate", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UpdateUser updateUser);

    ResponseUser toResponse(User user);

    default String roleToString(Role role) {
        return (role == null) ? "" : role.getName();
    }

}
