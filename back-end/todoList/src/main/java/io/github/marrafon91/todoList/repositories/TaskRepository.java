package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.dtos.PrioritySummaryDTO;
import io.github.marrafon91.todoList.entities.Task;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>,
        JpaSpecificationExecutor<Task> {

    @Override
    @EntityGraph(attributePaths = "category")
    List<Task> findAll();

    @Override
    @EntityGraph(attributePaths = "category")
    List<Task> findAll(org.springframework.data.jpa.domain.@NonNull Specification<Task> specification);

    Long countByDoneFalse();

    Long countByDoneTrue();

    @Query("""
            SELECT new io.github.marrafon91.todoList.dtos.PrioritySummaryDTO(
                t.priority,
                '',
                COUNT(t.id)
            )
            FROM Task t
            GROUP BY t.priority
            ORDER BY t.priority
            """)
    List<PrioritySummaryDTO> findPrioritySummary();

    @Query("""
            SELECT COUNT(t)
            FROM Task t
            WHERE t.done = false
            """)
    Long countPendingTasks();

    @Query("""
            SELECT COUNT(t)
            FROM Task t
            WHERE t.done = true
            """)
    Long countCompletedTasks();

    @Query("""
            SELECT COUNT(t)
            FROM Task t
            WHERE t.priority = 'HIGH'
            """)
    Long countHighPriorityTasks();

    @Query("""
            SELECT new io.github.marrafon91.todoList.dtos.PrioritySummaryDTO(
                t.priority,
                'Alta prioridade',
                COUNT(t)
            )
            FROM Task t
            WHERE t.priority = io.github.marrafon91.todoList.entities.Priority.HIGH
            GROUP BY t.priority
            """)
    List<PrioritySummaryDTO> findPrioritySummaryHigh();

}