package finalproject;

public class User {
    
    private String userId;
    private String username;
    private ShoppingCart shoppingCart;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    
}
