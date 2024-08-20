import DataAccessLayer.User;
import DataAccessLayer.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void testSaveUser() {
        // Тестируем сохранение пользователя
        User user = new User("testUser", "passwordHash", "salt", ZonedDateTime.now());
        userRepository.saveUser(user);

        User savedUser = userRepository.findUserByUsername("testUser");
        assertNotNull(savedUser, "User should be saved and retrieved from the repository");
        assertEquals("testUser", savedUser.getUsername(), "Usernames should match");
    }

    @Test
    void testFindUserByUsername() {
        // Тестируем поиск пользователя по имени
        User user = new User("testUser", "passwordHash", "salt", ZonedDateTime.now());
        userRepository.saveUser(user);

        User foundUser = userRepository.findUserByUsername("testUser");
        assertNotNull(foundUser, "User should be found by username");
        assertEquals("testUser", foundUser.getUsername(), "Usernames should match");
    }

    @Test
    void testIsUsernameTaken() {
        // Тестируем проверку, что имя пользователя занято
        User user = new User("testUser", "passwordHash", "salt", ZonedDateTime.now());
        userRepository.saveUser(user);

        assertTrue(userRepository.isUsernameTaken("testUser"), "Username should be marked as taken");
        assertFalse(userRepository.isUsernameTaken("nonExistentUser"), "Non-existent username should not be marked as taken");
    }
}
