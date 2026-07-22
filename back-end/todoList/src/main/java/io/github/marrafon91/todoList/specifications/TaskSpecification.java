package io.github.marrafon91.todoList.specifications;

import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> titleContains(String title) {
        return (root, query, builder) -> {
            if (title == null || title.isBlank()) {
                return null;
            }
            return builder.like(
                    builder.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Task> done(Boolean done) {
        return (root, query, builder) -> {
            if (done == null) {
                return null;
            }
            return builder.equal(root.get("done"), done);
        };
    }

    public static Specification<Task> priority(Priority priority) {
        return (root, query, builder) -> {
            if (priority == null) {
                return null;
            }
            return builder.equal(root.get("priority"), priority);
        };
    }

    public static Specification<Task> category(Long categoryId) {
        return (root, query, builder) -> {
            if (categoryId == null) {
                return null;
            }
            return builder.equal(
                    root.get("category").get("id"),
                    categoryId
            );
        };
    }
}
