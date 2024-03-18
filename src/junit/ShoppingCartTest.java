package finalproject;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingCartTest {

    private ShoppingCart cart;
    private Product product1;
    private Product product2;

    @Before
    public void setUp() {
        cart = ShoppingCart.getInstance();
        cart.clearCart(); // Ensure the cart is empty before each test

        // Mock or simple Product implementations for testing
        product1 = new Product() {
            @Override
            public String getName() {
                return "Product 1";
            }

            @Override
            public double getPrice() {
                return 10.0;
            }

            @Override
            public String getCategory() {
                return "Category 1";
            }
        };

        product2 = new Product() {
            @Override
            public String getName() {
                return "Product 2";
            }

            @Override
            public double getPrice() {
                return 20.0;
            }

            @Override
            public String getCategory() {
                return "Category 2";
            }
        };
    }

    @Test
    public void testAddItem() {
        cart.addItem(product1);
        assertEquals("Cart size should be 1 after adding one product.", 1, cart.getCartSize());
    }

    @Test
    public void testRemoveItem() {
        cart.addItem(product1);
        cart.removeItem(product1, 1);
        assertEquals("Cart should be empty after removing the product.", 0, cart.getCartSize());
    }

    @Test
    public void testClearCart() {
        cart.addItem(product1);
        cart.addItem(product2);
        cart.clearCart();
        assertEquals("Cart should be empty after clearing.", 0, cart.getCartSize());
    }

    @Test
    public void testGetTotalPrice() {
        cart.addItem(product1); // 10.0
        cart.addItem(product2); // 20.0
        cart.addItem(product2); // Another 20.0
        assertEquals("Total price should be 50.0", 50.0, cart.getTotalPrice(), 0.001);
    }
}
