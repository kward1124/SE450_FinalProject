package finalproject;

import java.util.UUID;

public class UserService {

    private Auth authenticator;

    public UserService(Auth authenticator) {
        this.authenticator = authenticator;
    }

    public boolean registerUser(String username, String password) {
        // Generate a unique user ID
        String userId = generateUniqueId();
        return authenticator.register(username, password, userId); 
    }

    public boolean loginUser(String username, String password) {
        return authenticator.login(username, password);
    }

    private String generateUniqueId() {
        // Using UUID to generate a unique identifier
        return UUID.randomUUID().toString();
    }
}

