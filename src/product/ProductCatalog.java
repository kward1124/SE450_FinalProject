package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    public List<Product> products = new ArrayList<>(); // List of products

    // Load the products from the file
    public void loadProducts(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // Read the file line by line
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(",");
                if (productData.length == 3) {
                    String category = productData[0].trim().replaceAll("\\p{C}", ""); // Get the category
                    String name = productData[1].trim(); // Get the name
                    double price = Double.parseDouble(productData[2].trim()); // Get the price
                    ProductFactory factory = new ProductFactory();
                    Product product = factory.getProduct(category, name, price); // Create the product
                    if (product != null) {
                        products.add(product); // Add the product to the list
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage()); // Error handling for reading the file
        } catch (NumberFormatException e) {
            System.err.println("Error parsing the price: " + e.getMessage()); // Error handling for parsing the price
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating the product: " + e.getMessage()); // Error handling for creating the product
        }
    }

    // Get the list of products
    public List<Product> getProducts() {
        return products;
    }
}
