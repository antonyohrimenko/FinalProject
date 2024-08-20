package BusinessLayer;

import DataAccessLayer.User;
import DataAccessLayer.UserRepository;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public void registerUser(String username, String password) {

        validateUser(username, password); // Валидация в отдельном методе

        String salt = passwordEncryptor.generateSalt();
        String encryptedPassword = passwordEncryptor.encrypt(password, salt);
        User user = new User(username, encryptedPassword, salt, ZonedDateTime.now());
        userRepository.saveUser(user);
    }

    private void validateUser(String username, String password) {
        if (userRepository.isUsernameTaken(username)) {
            throw new IllegalArgumentException("Имя пользователя уже занято!");
        }
        if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*].*")) {
            throw new IllegalArgumentException("Пароль должен содержать минимум 8 символов, цифру и специальный символ!");
        }
    }
}