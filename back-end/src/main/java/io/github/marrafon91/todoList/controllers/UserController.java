package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.UserDTO;
import io.github.marrafon91.todoList.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Usuários", description = "Operações relacionadas ao gerenciamento das usuários.")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation( summary = "Listar Usuários",
            description = "Retorna todos os usuários cadastrados no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de todos usuários retornada com sucesso")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> result = userService.findAllUser();
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Buscar usuários por ID",
            description = "Retorna um usuário específico pelo seu identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> findById(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    ) {
        UserDTO dto = userService.findUserById(id);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(
            summary = "Buscar usuários por Nome",
            description = "Retorna um usuário específico pelo seu Nome."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<UserDTO>> findByName(@RequestParam(defaultValue = "") String name) {
        List<UserDTO> result = userService.findUserByName(name);
        return ResponseEntity.ok(result);
    }
}
