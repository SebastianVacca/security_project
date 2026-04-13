package com.devcode.svacca.security_project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devcode.svacca.security_project.model.users.dto.CreateUser;
import com.devcode.svacca.security_project.model.users.dto.ResponseUser;
import com.devcode.svacca.security_project.model.users.dto.UpdateUser;
import com.devcode.svacca.security_project.service.users.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    @GetMapping
    public List<ResponseUser> getAll() {
        return userService.getAll();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseUser create(@RequestBody @Valid CreateUser request) {
        return userService.create(request);
    }

    @PutMapping("/edit/{username}")
    public ResponseUser update(@PathVariable String username, @RequestBody @Valid UpdateUser request) {
        return userService.update(request);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping("/{username}/activate")
    public void activate(@PathVariable String username){
        userService.activate(username);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping("/{username}/deactivate")
    public void deactivate(@PathVariable String username){
        userService.deactivate(username);
    }


    
    
}
