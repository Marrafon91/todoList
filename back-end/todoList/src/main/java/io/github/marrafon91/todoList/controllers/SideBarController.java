package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.SidebarDTO;
import io.github.marrafon91.todoList.services.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sidebar")
public class SideBarController {

    @Autowired
    private SidebarService sidebarService;

    @GetMapping
    public ResponseEntity<SidebarDTO> findSidebar() {
        SidebarDTO dto = sidebarService.findSidebar();
        return ResponseEntity.ok(dto);
    }
}
