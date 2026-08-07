package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.DashboardDTO;
import io.github.marrafon91.todoList.services.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "Operações relacionadas aos indicadores do Dashboard.")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @Operation(
            summary = "Obter informações do Dashboard",
            description = "Retorna a saudação, data atual, quantidade de tarefas pendentes, tarefas de alta prioridade e os indicadores exibidos nos cards do Dashboard."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informações do Dashboard retornadas com sucesso")
    })

    @GetMapping(produces = "application/json")
    public ResponseEntity<DashboardDTO> getDashboard() {
        DashboardDTO dto = dashboardService.getDashboard();
        return ResponseEntity.ok(dto);
    }
}
