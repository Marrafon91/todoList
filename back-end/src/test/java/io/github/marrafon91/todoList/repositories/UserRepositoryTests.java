package io.github.marrafon91.todoList.repositories;

import io.github.marrafon91.todoList.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByNameShouldReturnUsersWhenNameMatches() {

        List<User> result = userRepository.findByName("Guilherme");

        assertNotNull(result);
        assertFalse(result.isEmpty());

        User foundUser = result.stream()
                .filter(user -> user.getName().equals("Guilherme"))
                .findFirst()
                .orElseThrow();

        assertEquals("Guilherme", foundUser.getName());
        assertEquals("guilherme@gmail.com", foundUser.getEmail());
        assertEquals("$2a$10$iDmM6jJjiY33EfO9HB9NbOg9nnZageUXxNyIsvJzrPDHpHDcLatES", foundUser.getPassword());
    }

    @Test
    public void findByNameShouldReturnUsersWhenNameIsPartial() {

        List<User> result = userRepository.findByName("Guil");

        assertNotNull(result);
        assertFalse(result.isEmpty());

        assertTrue(result.stream().anyMatch(user ->
                user.getName().equals("Guilherme")));
    }

    @Test
    public void findByNameShouldIgnoreCase() {

        List<User> result = userRepository.findByName("GUILHERME");

        assertNotNull(result);
        assertFalse(result.isEmpty());

        assertTrue(result.stream().anyMatch(user ->
                        user.getName().equals("Guilherme")));
    }

    @Test
    public void findByNameShouldReturnEmptyListWhenUserDoesNotExist() {
        List<User> result = userRepository.findByName("Abelardo");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
