package com.devcode.svacca.security_project.service.users;

import java.util.List;

import com.devcode.svacca.security_project.model.users.dto.CreateUser;
import com.devcode.svacca.security_project.model.users.dto.ResponseUser;
import com.devcode.svacca.security_project.model.users.dto.UpdateUser;

public interface UserService {
    ResponseUser create(CreateUser request);
    ResponseUser update(UpdateUser request);
    List<ResponseUser> getAll();
    ResponseUser getByName(String name) ;
    void activate(String username);
    void deactivate(String username);
}
