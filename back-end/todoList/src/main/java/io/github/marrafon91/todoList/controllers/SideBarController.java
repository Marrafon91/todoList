package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.SidebarDTO;
import io.github.marrafon91.todoList.services.SidebarService;
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
@RequestMapping("/api/sidebar")
@Tag(
        name = "Sidebar",
        description = "Operações relacionadas aos indicadores exibidos na Sidebar."
)
public class SideBarController {

    @Autowired
    private SidebarService sidebarService;

    @Operation(
            summary = "Obter informações da Sidebar",
            description = "Retorna o resumo das tarefas, incluindo quantidade total, pendentes, concluídas, prioridades e categorias."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações da Sidebar retornadas com sucesso")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<SidebarDTO> findSidebar() {
        SidebarDTO dto = sidebarService.findSidebar();
        return ResponseEntity.ok(dto);
    }
}
