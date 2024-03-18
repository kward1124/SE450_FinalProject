package finalproject;

public interface Auth {
    boolean login(String username, String password);
    boolean register(String username, String password, String userId);
    String getUserId(String username);
}

