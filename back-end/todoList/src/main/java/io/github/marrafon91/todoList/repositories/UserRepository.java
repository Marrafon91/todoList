package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByName(@Param("name") String name);
}
