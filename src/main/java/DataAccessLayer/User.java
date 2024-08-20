package DataAccessLayer;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class User {
    private String username;
    private String passwordHash;
    private String salt;
    private ZonedDateTime createdAt;

    public User(String username, String passwordHash, String salt, ZonedDateTime createdAt) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

}