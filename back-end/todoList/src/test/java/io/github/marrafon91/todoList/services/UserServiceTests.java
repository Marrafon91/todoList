package io.github.marrafon91.todoList.services;

import io.github.marrafon91.todoList.dtos.UserDTO;
import io.github.marrafon91.todoList.entities.User;
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

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Long userId, nonUserId;

    private User user;

    @BeforeEach
    public void setUp() {
        userId = 1L;
        nonUserId = 2L;

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
}
