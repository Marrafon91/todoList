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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private Long nonExistingId;

    @BeforeEach
    void setUp() {
        tasks = taskRepository.findAll();
        existingId = tasks.getFirst().getId();
        nonExistingId = 999L;
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
        result.andExpect(jsonPath("$[1].title").value("Organizar documentos"));
        result.andExpect(jsonPath("$[0].description").value(tasks.getFirst().getDescription()));
        result.andExpect(jsonPath("$[1].description").value(tasks.get(1).getDescription()));
        result.andExpect(jsonPath("$[0].done").value(tasks.getFirst().isDone()));
        result.andExpect(jsonPath("$[0].priority").value(tasks.getFirst().getPriority().name()));
        result.andExpect(jsonPath("$[0].createdAt").value(tasks.getFirst().getCreatedAt().withNano(0).toString()));
        result.andExpect(jsonPath("$[0].dueDate").value(tasks.getFirst().getDueDate().toString()));
    }

    @Test
    void findByIdShouldReturnTaskWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/tasks/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.title").value(tasks.getFirst().getTitle()));
        result.andExpect(jsonPath("$.description").value(tasks.getFirst().getDescription()));

    }

    @Test
    void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/tasks/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isNotFound());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void toggleDoneShouldChangeTaskDone() throws Exception {

        Task task = tasks.getFirst();
        boolean doneBefore = task.isDone();

        mockMvc.perform(
                        patch("/api/tasks/{id}/done", task.getId())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(task.getId()))
                .andExpect(jsonPath("$.done").value(!doneBefore));
    }

    @Test
    void toggleDoneShouldToggleTaskDoneTwice() throws Exception {

        Task task = tasks.getFirst();
        boolean doneBefore = task.isDone();

        mockMvc.perform(patch("/api/tasks/{id}/done", task.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/api/tasks/{id}/done", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done").value(doneBefore));
    }

    @Test
    void insertShouldReturnCreatedWhenTaskIsValid() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "title": "Estudar Java",
                                "description": "Estudar testes de controller",
                                "priority": "HIGH",
                                "categoryId": 1,
                                "dueDate": "2026-08-10"
                            }
                            """)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isCreated());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.title").value("Estudar Java"));
        result.andExpect(jsonPath("$.description").value("Estudar testes de controller"));
        result.andExpect(jsonPath("$.done").value(false));
        result.andExpect(jsonPath("$.priority").value("HIGH"));

        result.andExpect(header().exists("Location"));
    }

}
