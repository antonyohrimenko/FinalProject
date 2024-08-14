package BusinessLayer;

public interface PasswordEncryptor {
    String encrypt(String password, String salt);
    String generateSalt();
}
