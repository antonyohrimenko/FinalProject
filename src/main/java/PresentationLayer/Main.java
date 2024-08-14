package PresentationLayer;

import BusinessLayer.AuthService;
import BusinessLayer.AuthServiceImpl;
import BusinessLayer.PasswordEncryptor;
import BusinessLayer.PasswordEncryptorImpl;
import BusinessLayer.RegistrationService;
import BusinessLayer.RegistrationServiceImpl;
import DataAccessLayer.UserRepository;

public class Main {
    public static void main(String[] args) {
        // Создание репозитория для хранения пользователей
        UserRepository userRepository = new UserRepository();

        // Создание шифратора паролей
        PasswordEncryptor passwordEncryptor = new PasswordEncryptorImpl();

        // Создание сервисов регистрации и авторизации
        RegistrationService registrationService = new RegistrationServiceImpl(userRepository, passwordEncryptor);
        AuthService authService = new AuthServiceImpl(userRepository, passwordEncryptor);

        // Создание и запуск консольного интерфейса
        ConsoleUI consoleUI = new ConsoleUI(registrationService, authService);
        consoleUI.start();
    }
}