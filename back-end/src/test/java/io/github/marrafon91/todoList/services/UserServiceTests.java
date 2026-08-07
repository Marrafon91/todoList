package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.UserDTO;
import io.github.marrafon91.todoList.entities.User;
import io.github.marrafon91.todoList.exceptions.ResourceNotFoundException;
import io.github.marrafon91.todoList.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Long userId, nonExistingUserId;

    private User user;

    @BeforeEach
    public void setUp() {
        userId = 1L;
        nonExistingUserId = 2L;

        user = new User();
        user.setId(userId);
        user.setName("testName");
        user.setPassword("testPassword");
        user.setEmail("testEmail@gmail.com");
    }

    @Test
    public void findAllShouldReturnUserDTOListWhenUsersExist() {

        Mockito.when(userRepository.findAll())
                .thenReturn(List.of(user));

        List<UserDTO> result = userService.findAllUser();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());

        UserDTO resultUser = result.getFirst();

        Assertions.assertEquals(userId, resultUser.id());
        Assertions.assertEquals("testName", resultUser.name());
        Assertions.assertEquals("testEmail@gmail.com", resultUser.email());

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findByIdShouldReturnUserDTOWhenUsersExist() {
        Mockito.when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        UserDTO result = userService.findUserById(userId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userId, result.id());
        Assertions.assertEquals("testName", result.name());

        Mockito.verify(userRepository).findById(userId);
    }

    @Test
    public void findByIdShouldReturnResourceNotFoundExceptionWhenUsersDoesNotExist() {

        Mockito.when(userRepository.findById(nonExistingUserId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows
                (ResourceNotFoundException.class,
                        () -> userService.findUserById(nonExistingUserId)
                );

        Assertions.assertEquals(
                "Usúario com ID " + nonExistingUserId + " não encontrado",
                exception.getMessage()
        );

        Mockito.verify(userRepository).findById(nonExistingUserId);
    }

    @Test
    public void findUserByNameShouldReturnUserDTOWhenUsersExist() {

        Mockito.when(userRepository.findByName("testName"))
                .thenReturn(List.of(user));

        List<UserDTO> result = userService.findUserByName("testName");

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.size());

        UserDTO resultUser = result.getFirst();

        Assertions.assertEquals(userId, resultUser.id());
        Assertions.assertEquals("testName", resultUser.name());
        Assertions.assertEquals("testEmail@gmail.com", resultUser.email());

        Mockito.verify(userRepository).findByName("testName");
    }

    @Test
    public void findUserByNameShouldThrowResourceNotFoundExceptionWhenUserDoesNotExist() {

        String invalidName = "invalidName";

        Mockito.when(userRepository.findByName(invalidName))
                .thenReturn(List.of());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> userService.findUserByName(invalidName)
        );

        Assertions.assertEquals(
                "Usuário com nome " + invalidName + " não encontrado",
                exception.getMessage()
        );

        Mockito.verify(userRepository)
                .findByName(invalidName);
    }
}
