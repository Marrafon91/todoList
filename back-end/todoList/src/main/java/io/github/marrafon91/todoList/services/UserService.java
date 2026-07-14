package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.UserDTO;
import io.github.marrafon91.todoList.entities.User;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
import io.github.marrafon91.todoList.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAllUser() {
        List<User> result = userRepository.findAll();
        return result.stream().map(UserDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Usúario com ID " + id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findUserByName(String name) {
        List<User> result = userRepository.findByName(name);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Usúario com nome " + name + " não encontrado");
        }
        return result.stream().map(UserDTO::new).toList();
    }
}
