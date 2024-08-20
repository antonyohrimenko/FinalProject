
import BusinessLayer.AuthServiceImpl;
import BusinessLayer.PasswordEncryptor;
import BusinessLayer.PasswordEncryptorImpl;
import DataAccessLayer.User;
import DataAccessLayer.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    private AuthServiceImpl authService;
    private UserRepository userRepository;
    private PasswordEncryptor passwordEncryptor;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        passwordEncryptor = new PasswordEncryptorImpl();
        authService = new AuthServiceImpl(userRepository, passwordEncryptor);

        // Создание пользователя с зашифрованным паролем
        String rawPassword = "password";
        String salt = passwordEncryptor.generateSalt();  // Генерируем соль
        String encryptedPassword = passwordEncryptor.encrypt(rawPassword, salt);  // Шифруем пароль

        // Создание пользователя с зашифрованным паролем и солью
        User user = new User("testUser", encryptedPassword, salt, ZonedDateTime.now());
        userRepository.saveUser(user);  // Сохраняем пользователя с зашифрованным паролем и солью
    }

    @Test
    void testLoginSuccessful() {
        // Тестируем успешный вход пользователя с правильным паролем
        assertTrue(authService.authenticate("testUser", "password"));
    }

    @Test
    void testLoginFailed() {
        // Тестируем неуспешный вход пользователя с неверным паролем
        assertFalse(authService.authenticate("testUser", "wrongPassword"));
    }

    @Test
    void testLoginNonExistentUser() {
        // Тестируем вход пользователя, которого не существует
        assertFalse(authService.authenticate("nonExistentUser", "password"));
    }
}