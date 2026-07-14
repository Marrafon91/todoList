package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.UserDTO;
import io.github.marrafon91.todoList.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> result = userService.findAllUser();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = userService.findUserById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> findByName(@RequestParam(defaultValue = "") String name) {
        List<UserDTO> result = userService.findUserByName(name);
        return ResponseEntity.ok(result);
    }
}
