package finalproject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserAuth implements Auth {

    private Map<String, UserCredentials> userCreds = new HashMap<>();
    private final String CREDENTIALS_FILE = "userCredentials.txt";

    public UserAuth() {
        loadUserCredentials();
    }

    @Override
    public boolean login(String username, String password) {
        if (!userCreds.containsKey(username)) {
            return false;
        }
        return userCreds.get(username).getPassword().equals(password);
    }

    @Override
    public boolean register(String username, String password, String userId) {
        if (userCreds.containsKey(username)) {
            return false;
        }
        userCreds.put(username, new UserCredentials(userId, password));
        saveUserCredentials();
        return true;
    }

    @Override
    public String getUserId(String username) {
        UserCredentials credentials = userCreds.get(username);
        return credentials != null ? credentials.getUserId() : null;
    }


    private void saveUserCredentials() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE))) {
            for (Map.Entry<String, UserCredentials> entry : userCreds.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue().getUserId() + "," + entry.getValue().getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    userCreds.put(parts[0], new UserCredentials(parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class UserCredentials {
        private final String userId;
        private final String password;

        public UserCredentials(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }

        public String getUserId() {
            return userId;
        }

        public String getPassword() {
            return password;
        }
    }
}
