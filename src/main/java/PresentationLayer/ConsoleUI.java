package PresentationLayer;

import BusinessLayer.AuthService;
import BusinessLayer.RegistrationService;
import java.util.Scanner;

public class ConsoleUI {

    private final RegistrationService registrationService;
    private final AuthService authService;
    private final Scanner scanner;

    public ConsoleUI(RegistrationService registrationService, AuthService authService) {
        this.registrationService = registrationService;
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать! Выберите действие:");
        while (true) {
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            System.out.println("3. Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    authenticate();
                    break;
                case "3":
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void register() {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        try {
            registrationService.registerUser(username, password);
            System.out.println("Регистрация прошла успешно!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
        }
    }

    private void authenticate() {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        try {
            boolean success = authService.authenticate(username, password);
            if (success) {
                System.out.println("Авторизация прошла успешно!");
            } else {
                System.out.println("Ошибка авторизации: неверные данные.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка авторизации: " + e.getMessage());
        }
    }
}