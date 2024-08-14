package BusinessLayer;

import DataAccessLayer.User;
import DataAccessLayer.UserRepository;

public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return false;
        }
        String encryptedPassword = passwordEncryptor.encrypt(password, user.getSalt());
        return user.getPasswordHash().equals(encryptedPassword);
    }
}
