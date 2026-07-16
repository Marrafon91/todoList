package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.dtos.CategorySummaryDTO;
import io.github.marrafon91.todoList.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("""
            SELECT new io.github.marrafon91.todoList.dtos.CategorySummaryDTO(
                            c.id,
                            c.name,
                            c.color,
                            COUNT(t.id)
                        )
                        FROM Category c
                        LEFT JOIN c.tasks t
                        GROUP BY
                            c.id,
                            c.name,
                            c.color
                        ORDER BY c.name
            """)
    List<CategorySummaryDTO> findCategorySummary();
}
