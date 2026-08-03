package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.entities.Task;
import io.github.marrafon91.todoList.repositories.TaskRepository;
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
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    private List<Task> tasks;
    private Long existingId;

    @BeforeEach
    void setUp() {
        tasks = taskRepository.findAll();
        existingId = tasks.getFirst().getId();
    }

    @Test
    void findAllShouldReturnAllTasks() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/tasks")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.length()").value(tasks.size()));
        result.andExpect(jsonPath("$[0].id").value(tasks.getFirst().getId()));
        result.andExpect(jsonPath("$[0].title").value(tasks.getFirst().getTitle()));
        result.andExpect(jsonPath("$[0].description").value(tasks.getFirst().getDescription()));
        result.andExpect(jsonPath("$[0].done").value(tasks.getFirst().isDone()));
        result.andExpect(jsonPath("$[0].priority").value(tasks.getFirst().getPriority().name()));
        result.andExpect(jsonPath("$[0].createdAt").value(tasks.getFirst().getCreatedAt().withNano(0).toString()));
        result.andExpect(jsonPath("$[0].dueDate").value(tasks.getFirst().getDueDate().toString()));
    }
}