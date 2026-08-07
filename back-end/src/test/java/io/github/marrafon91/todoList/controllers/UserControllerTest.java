package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.entities.User;
import io.github.marrafon91.todoList.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    List<User> users;
    private Long existingUserId, nonExistingUserId;
    private String existingUserName;

    @BeforeEach
    void setUp() {
        users = userRepository.findAll();
        existingUserId = users.getFirst().getId();
        existingUserName = users.getFirst().getName();
        nonExistingUserId = 999L;
    }

  @Test
  void usersShouldReturnAllUsers() throws Exception {

      ResultActions result = mockMvc.perform(
              get("/api/user")
                      .accept(MediaType.APPLICATION_JSON)
      );
      result.andExpect(status().isOk());
      result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
      result.andExpect(jsonPath("$").isArray());
      result.andExpect(jsonPath("$.size()").value(users.size()));
      result.andExpect(jsonPath("$.[0].id").value(users.getFirst().getId()));
      result.andExpect(jsonPath("$.[1].id").value(users.get(1).getId()));
      result.andExpect(jsonPath("$.[0].name").value(users.getFirst().getName()));
      result.andExpect(jsonPath("$.[1].name").value("Maria"));
      result.andExpect(jsonPath("$.[0].email").value(users.getFirst().getEmail()));
      result.andExpect(jsonPath("$.[1].email").value("maria@gmail.com"));
    }

    @Test
    void userFindByIdShouldReturnUsersWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/user/{id}", existingUserId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.id").value(existingUserId));
        result.andExpect(jsonPath("$.name").value(users.getFirst().getName()));
        result.andExpect(jsonPath("$.email").value(users.getFirst().getEmail()));
        }

    @Test
    void userFindByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/user/{id}", nonExistingUserId)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isNotFound());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.error").value("Usúario com ID " + nonExistingUserId + " não encontrado"));
    }

    @Test
    void userFindByNameShouldReturnUsersWhenNameExists() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/user/search")
                        .param("name", existingUserName)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        result.andExpect(jsonPath("$").isArray());
        result.andExpect(jsonPath("$.[0].name").value(users.getFirst().getName()));
        result.andExpect(jsonPath("$.[0].email").value(users.getFirst().getEmail()));
    }

    @Test
    void userFindByNameShouldThrowResourceNotFoundExceptionWhenNameDoesNotExist() throws Exception {

        String name = "Joao Felipe";

        ResultActions result = mockMvc.perform(
                get("/api/user/search")
                        .param("name", name)
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isNotFound());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.error").value("Usuário com nome " + name + " não encontrado"));
    }
}
