package io.github.marrafon91.todoList.controllers;

import io.github.marrafon91.todoList.dtos.DashboardDTO;
import io.github.marrafon91.todoList.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard() {
        DashboardDTO dto = dashboardService.getDashboard();
        return ResponseEntity.ok(dto);
    }
}
