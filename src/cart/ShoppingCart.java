package finalproject;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private static ShoppingCart instance;
    private Map<Product, Integer> cartItems;

    private ShoppingCart() {
        cartItems = new HashMap<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(Product product) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + 1);
    }

    public void removeItem(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            int newQuantity = Math.max(0, currentQuantity - quantity);
            if (newQuantity == 0) {
                cartItems.remove(product);
            } else {
                cartItems.put(product, newQuantity);
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }
    
    public void listItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }

        cartItems.forEach((product, quantity) -> System.out.println(quantity + " x " + product.getName() + " | Category: " + product.getCategory() + " | Price: $" + product.getPrice()));
        System.out.println("Total Price: $" + getTotalPrice());
    }

    public double getTotalPrice() {
        return cartItems.entrySet().stream().mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
    }

    public int getCartSize() {
        return cartItems.size();
    }

    
    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }
}

