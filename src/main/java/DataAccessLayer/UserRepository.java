package DataAccessLayer;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map <String, User> users = new HashMap<>();


    // метод проверяет занято ли уже такое имя пользователя для регистрации
    public boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }

    // метод добавления пользователя в "базу"
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
    }

    // метод поиска пользователя по "ключу". В нашем случае логину
    public User findUserByUsername(String username) {
        return users.get(username);
    }
}
