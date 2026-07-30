package io.github.marrafon91.todoList.specifications;

import io.github.marrafon91.todoList.entities.Category;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class TaskSpecificationTests {

    @Mock
    private Root<Task> root;
    @Mock
    private CriteriaQuery<?> query;
    @Mock
    private CriteriaBuilder builder;
    @Mock
    private Path<String> titlePath;
    @Mock
    private Path<Boolean> donePath;
    @Mock
    private Path<Priority> priorityPath;
    @Mock
    private Path<Category> categoryPath;
    @Mock
    private Path<Long> categoryIdPath;
    @Mock
    private Expression<String> lowerTitlePath;
    @Mock
    private Predicate predicate;

    @Test
    public void titleContainsShouldReturnPredicateWhenTitleIsValid() {

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

        Mockito.when(root.<Category>get("category"))
                .thenReturn(categoryPath);

        Mockito.when(categoryPath.<Long>get("id"))
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