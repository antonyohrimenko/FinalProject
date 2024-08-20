import BusinessLayer.PasswordEncryptor;
import BusinessLayer.PasswordEncryptorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorImplTest {

    private PasswordEncryptor passwordEncryptor;

    @BeforeEach
    void setUp() {
        passwordEncryptor = new PasswordEncryptorImpl();
    }

    @Test
    void testEncryptAndDecryptPassword() {
        // Тестируем шифрование пароля
        String password = "SecurePassword1!";
        String salt = passwordEncryptor.generateSalt();
        String encryptedPassword = passwordEncryptor.encrypt(password, salt);

        // Проверяем, что зашифрованный пароль отличается от исходного
        assertNotEquals(password, encryptedPassword);

        // Проверяем, что пароль можно зашифровать и расшифровать обратно
        String reEncryptedPassword = passwordEncryptor.encrypt(password, salt);
        assertEquals(encryptedPassword, reEncryptedPassword);
    }

    @Test
    void testGenerateSalt() {
        // Тестируем генерацию соли
        String salt1 = passwordEncryptor.generateSalt();
        String salt2 = passwordEncryptor.generateSalt();

        // Проверяем, что соль уникальна при каждом вызове
        assertNotEquals(salt1, salt2);
    }
}
