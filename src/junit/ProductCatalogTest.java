package finalproject;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductCatalogTest {

    private ProductCatalog catalog;
    private File tempFile;

    @Before
    public void setUp() throws IOException {
        catalog = new ProductCatalog();
        // Create a temporary file for testing.
        tempFile = File.createTempFile("test_products", ".txt");
    }

    private void writeToFile(String content) throws IOException {
        // Write the content to the temporary file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
    }

    @Test
    public void testProductLoadSuccessfully() throws IOException {
        // Successful load scenario
        writeToFile("Appliance,Refrigerator,1200.00\nAppliance,Dishwasher,800.00");
        catalog.loadProducts(tempFile.getAbsolutePath());
        List<Product> products = catalog.getProducts();
        assertEquals("Should load 2 products", 2, products.size());
    }

    @Test
    public void testProductLoadWithInvalidFormat() throws IOException {
        // Invalid format scenario  
        writeToFile("InvalidFormat");
        catalog.loadProducts(tempFile.getAbsolutePath());
        assertTrue("List should be empty due to invalid format", catalog.getProducts().isEmpty());
    }

    @Test
    public void testProductLoadEmptyFile() throws IOException {
        // Empty file scenario
        writeToFile("");
        catalog.loadProducts(tempFile.getAbsolutePath());
        assertTrue("List should be empty for empty file", catalog.getProducts().isEmpty());
    }

    @Test
public void testProductLoadWithInvalidPrice() throws IOException {
    writeToFile("Appliance,Toaster,not_a_price");

    // When loading products
    catalog.loadProducts(tempFile.getAbsolutePath());

    assertTrue("List should be empty or handle invalid price accordingly", catalog.getProducts().isEmpty());
}


    @After
    public void tearDown() {
        // Delete the temporary file after each test
        if (tempFile != null) {
            tempFile.delete();
        }
    }
}
