package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SideBarControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findSidebarShouldReturnCorrectSidebarData() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/sidebar")
                        .accept(MediaType.APPLICATION_JSON)
        );
        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        );

        result.andExpect(jsonPath("$.totalTasks").exists());
        result.andExpect(jsonPath("$.pendingTasks").exists());
        result.andExpect(jsonPath("$.completedTasks").exists());

        result.andExpect(jsonPath("$.priorities").isArray());
        result.andExpect(jsonPath("$.categories").isArray());

        result.andExpect(jsonPath("$.priorities[0].priority").value("HIGH"));
        result.andExpect(jsonPath("$.priorities[0].label").value("Alta prioridade"));
        result.andExpect(jsonPath("$.priorities[0].quantity").exists());
    }

}
