package DataAccessLayer;

import java.time.LocalDateTime;

public class User {
    private String username;
    private String passwordHash;
    private String salt;
    private LocalDateTime createdAt;

    public User(String username, String passwordHash, String salt, LocalDateTime createdAt) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}