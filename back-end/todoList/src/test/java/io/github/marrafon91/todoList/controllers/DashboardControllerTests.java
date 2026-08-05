package io.github.marrafon91.todoList.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DashboardControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDashboardShouldReturnDashboardWithCards() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/dashboard")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk());
        result.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        result.andExpect(jsonPath("$.greeting").value("Olá, como você está?"));

        result.andExpect(jsonPath("$.currentDate").exists());
        result.andExpect(jsonPath("$.currentDate").isNotEmpty());
        result.andExpect(jsonPath("$.currentDate").value(LocalDate.now().toString()));

        result.andExpect(jsonPath("$.pendingTasks").exists());
        result.andExpect(jsonPath("$.highPriorityTasks").exists());

        result.andExpect(jsonPath("$.cards").isArray());
        result.andExpect(jsonPath("$.cards.length()").value(4));

        result.andExpect(jsonPath("$.cards[0].title").value("Total"));
        result.andExpect(jsonPath("$.cards[0].value").exists());

        result.andExpect(jsonPath("$.cards[1].title").value("Pendentes"));
        result.andExpect(jsonPath("$.cards[1].value").exists());

        result.andExpect(jsonPath("$.cards[2].title").value("Concluídas"));
        result.andExpect(jsonPath("$.cards[2].value").exists());

        result.andExpect(jsonPath("$.cards[3].title").value("Alta prioridade"));
        result.andExpect(jsonPath("$.cards[3].value").exists());
    }

}
