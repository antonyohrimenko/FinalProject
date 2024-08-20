import BusinessLayer.PasswordEncryptor;
import BusinessLayer.PasswordEncryptorImpl;
import BusinessLayer.RegistrationServiceImpl;
import DataAccessLayer.User;
import DataAccessLayer.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceImplTest {

    private RegistrationServiceImpl registrationService;
    private UserRepository userRepository;
    private PasswordEncryptor passwordEncryptor;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        passwordEncryptor = new PasswordEncryptorImpl();
        registrationService = new RegistrationServiceImpl(userRepository, passwordEncryptor);
    }

    @Test
    void testRegisterUserSuccessful() {
        // Тестируем успешную регистрацию пользователя
        String username = "newUser";
        String password = "NewPassword1!";

        registrationService.registerUser(username, password);

        User user = userRepository.findUserByUsername(username);
        assertNotNull(user, "User should be saved in the repository");
        assertEquals(username, user.getUsername(), "Usernames should match");
        assertNotNull(user.getPasswordHash(), "Password hash should not be null");
        assertNotNull(user.getSalt(), "Salt should not be null");
    }

    @Test
    void testRegisterUserWithExistingUsername() {
        // Тестируем регистрацию пользователя с уже существующим именем пользователя
        String username = "existingUser";
        String password = "ExistingPassword1!";

        registrationService.registerUser(username, password);  // Регистрация первого пользователя

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> registrationService.registerUser(username, password),
                "Expected registerUser() to throw, but it didn't"
        );

        assertEquals("Имя пользователя уже занято!", thrown.getMessage());
    }

    @Test
    void testRegisterUserWithInvalidPassword() {
        // Тестируем регистрацию пользователя с некорректным паролем
        String username = "userWithInvalidPassword";
        String password = "short";  // Недостаточная длина пароля

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> registrationService.registerUser(username, password),
                "Expected registerUser() to throw, but it didn't"
        );

        assertEquals("Пароль должен содержать минимум 8 символов, цифру и специальный символ!", thrown.getMessage());
    }
}
