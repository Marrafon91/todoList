package io.github.marrafon91.todoList.specifications;

import io.github.marrafon91.todoList.entities.Priority;
import io.github.marrafon91.todoList.entities.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecificationTests {

    private final Root<Task> root = Mockito.mock(Root.class);
    private final CriteriaQuery<?> query = Mockito.mock(CriteriaQuery.class);
    private final CriteriaBuilder builder = Mockito.mock(CriteriaBuilder.class);

    @Test
    public void titleContainsShouldReturnPredicateWhenTitleIsValid() {

        Path<String> titlePath = Mockito.mock(Path.class);
        Expression<String> lowerTitlePath = Mockito.mock(Expression.class);
        Predicate predicate = Mockito.mock(Predicate.class);

        Mockito.when(root.<String>get("title"))
                .thenReturn(titlePath);

        Mockito.when(builder.lower(titlePath))
                .thenReturn(lowerTitlePath);

        Mockito.when(builder.like(lowerTitlePath, "%java%"))
                .thenReturn(predicate);

        Specification<Task> specification =
                TaskSpecification.titleContains("Java");

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(predicate, result);

        Mockito.verify(root).get("title");
        Mockito.verify(builder).lower(titlePath);
        Mockito.verify(builder).like(lowerTitlePath, "%java%");
    }

    @Test
    public void titleContainsShouldReturnNullWhenTitleIsNull() {

        Specification<Task> specification =
                TaskSpecification.titleContains(null);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNull(result);

        Mockito.verifyNoInteractions(root, builder);
    }

    @Test
    public void titleContainsShouldReturnNullWhenTitleIsBlank() {

        Specification<Task> specification =
                TaskSpecification.titleContains("   ");

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNull(result);

        Mockito.verifyNoInteractions(root, builder);
    }

    @Test
    public void doneShouldReturnPredicateWhenDoneIsValid() {

        Path<Boolean> donePath = Mockito.mock(Path.class);
        Predicate predicate = Mockito.mock(Predicate.class);

        Mockito.when(root.<Boolean>get("done"))
                .thenReturn(donePath);

        Mockito.when(builder.equal(donePath, true))
                .thenReturn(predicate);

        Specification<Task> specification =
                TaskSpecification.done(true);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(predicate, result);

        Mockito.verify(root).get("done");
        Mockito.verify(builder).equal(donePath, true);
    }

    @Test
    public void doneShouldReturnNullWhenDoneIsNull() {

        Specification<Task> specification =
                TaskSpecification.done(null);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNull(result);

        Mockito.verifyNoInteractions(root, builder);
    }

    @Test
    public void priorityShouldReturnPredicateWhenPriorityIsValid() {

        Path<Priority> priorityPath = Mockito.mock(Path.class);
        Predicate predicate = Mockito.mock(Predicate.class);

        Mockito.when(root.<Priority>get("priority"))
                .thenReturn(priorityPath);

        Mockito.when(builder.equal(priorityPath, Priority.HIGH))
                .thenReturn(predicate);

        Specification<Task> specification =
                TaskSpecification.priority(Priority.HIGH);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(predicate, result);

        Mockito.verify(root).get("priority");
        Mockito.verify(builder).equal(priorityPath, Priority.HIGH);
    }

    @Test
    public void priorityShouldReturnNullWhenPriorityIsNull() {

        Specification<Task> specification =
                TaskSpecification.priority(null);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNull(result);

        Mockito.verifyNoInteractions(root, builder);
    }

    @Test
    public void categoryShouldReturnPredicateWhenCategoryIdIsValid() {

        Path<Object> categoryPath = Mockito.mock(Path.class);
        Path<Object> categoryIdPath = Mockito.mock(Path.class);
        Predicate predicate = Mockito.mock(Predicate.class);

        Mockito.when(root.get("category"))
                .thenReturn(categoryPath);

        Mockito.when(categoryPath.get("id"))
                .thenReturn(categoryIdPath);

        Mockito.when(builder.equal(categoryIdPath, 1L))
                .thenReturn(predicate);

        Specification<Task> specification =
                TaskSpecification.category(1L);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(predicate, result);

        Mockito.verify(root).get("category");
        Mockito.verify(categoryPath).get("id");
        Mockito.verify(builder).equal(categoryIdPath, 1L);
    }

    @Test
    public void categoryShouldReturnNullWhenCategoryIdIsNull() {

        Specification<Task> specification =
                TaskSpecification.category(null);

        Predicate result = specification.toPredicate(root, query, builder);

        Assertions.assertNull(result);

        Mockito.verifyNoInteractions(root, builder);
    }
}