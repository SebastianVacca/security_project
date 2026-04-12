package com.devcode.svacca.security_project.repository.roles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcode.svacca.security_project.model.users.dto.CreateUser;
import com.devcode.svacca.security_project.model.users.dto.ResponseUser;
import com.devcode.svacca.security_project.model.users.dto.UpdateUser;
import com.devcode.svacca.security_project.model.users.entity.User;


public interface RoleRepository extends JpaRepository<User, String>{
    public ResponseUser create(CreateUser request);
    public ResponseUser update(UpdateUser request);
    public List<ResponseUser> getAll();
    public ResponseUser getByName(String name) ;
}
