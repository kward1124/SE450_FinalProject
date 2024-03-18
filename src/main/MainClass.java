package finalproject;

import java.util.Scanner;

public class MainClass {

    private static final Scanner scanner = new Scanner(System.in);

    // method to ensure input is an integer when prompted
    // used to mitigate user input errors
	private static int readInt(String prompt, String errorMessage) {
        int number = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim(); // Read input as a string
                number = Integer.parseInt(input); // Convert string input to integer
                valid = true; // Input was successfully parsed; exit the loop
            } catch (NumberFormatException e) {
                System.out.println(errorMessage); // Inform user of the error and reprompt
            }
        }
        return number; // Return the valid integer
    }

    
    // method to ask for a yes or no response
    // used to mitigate user input errors
    private static String askYesNo(String message) {
        System.out.println(message);
        String response = scanner.nextLine().trim().toLowerCase();
        while (!"y".equals(response) && !"yes".equals(response) && !"n".equals(response) && !"no".equals(response)) {
            System.out.println("Please answer with Y/Yes or N/No.");
            response = scanner.nextLine().trim().toLowerCase();
        }
        return response;
    }
    

    private static final EntryLogger logger = new EntryLogger();
    private static final UserAuth userAuth = new UserAuth();
    private static final MockPaymentProcessor paymentProcessor = new MockPaymentProcessor();
    private static final PaymentPortal paymentPortal = new PaymentPortal(paymentProcessor);
    private static UserService userService = new UserService(userAuth);
    private static String currentUserId = null;
    private static String currentUsername = null;

    public static void main(String[] args) {

        String productFile = "products.txt";
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.loadProducts(productFile);

        boolean exit = false;
        boolean loginSuccess = false;

        while (!loginSuccess && !exit) {
            System.out.println("\nWelcome to Kevin's Hardware Store!");
            String previousUser = askYesNo("\nHave you shopped with us before?");

            // Login if the user is returning
            if (previousUser.equalsIgnoreCase("Y") || previousUser.equalsIgnoreCase("YES")) {
                System.out.println("Welcome back! Please log in to continue.");
                System.out.println("\nUsername: ");
                String username = scanner.nextLine().trim();
                System.out.println("\nPassword: ");
                String password = scanner.nextLine().trim();

                if (userService.loginUser(username, password)) {
                    System.out.println("\nLogin Successful!");
                    currentUserId = userAuth.getUserId(username);
                    currentUsername = username;
                    loginSuccess = true;
                    logger.log(currentUserId, username, "Login Successful");
                } else {
                    System.out.println("Login failed. Please try again.");
                    logger.log(currentUserId, username, "Login Unsuccessful");
                }
                // Registaration if the user is new
            } else if (previousUser.equalsIgnoreCase("N") || previousUser.equalsIgnoreCase("NO")) {
                System.out.println("\nWelcome! Let's get you registered.");
                System.out.println("\nChoose a username: ");
                String username = scanner.nextLine().trim();
                System.out.println("\nChoose a password: ");
                String password = scanner.nextLine().trim();

                if (userService.registerUser(username, password)) {
                    System.out.println("\nRegistration was successful! You are now logged in.");
                    loginSuccess = true; 
                    currentUserId = userAuth.getUserId(username);
                    currentUsername = username;
                    logger.log(currentUserId, username, "Registration Successful");
                } else {
                    System.out.println("\nRegistration failed. Username might already be taken.");
                    logger.log(currentUserId, username, "Registration Unsuccesful");
                }
            } else if (previousUser.equalsIgnoreCase("exit")) {
                exit = true;
            } else {
                System.out.println("Invalid input. Please answer with Y, N, or type 'exit' to quit.");
            }
        }
        
        
        while (!exit){


            System.out.println("\nMain Menu:");
            System.out.println("\n1. Browse Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Log Out\n");

            
            int choice = readInt("Please select your choice", "Invlaid choice. Please select an option 1-4.");

            switch(choice){

                case 1:
                    System.out.println("\nTaking you to the product pages...");
                    logger.log(currentUserId, currentUsername, "Viewed products in the store.");
                    viewAndAddProducts(scanner, productCatalog);
                    break;

                case 2:
                    System.out.println("\nViewing your cart...");
                    logger.log(currentUserId, currentUsername, "Viewed cart");
                    viewCart();
                    break;

                case 3:
                    System.out.println("\nChecking out...");
                    logger.log(currentUserId, currentUsername, "Initiated checkout process");
                    checkout(scanner,ShoppingCart.getInstance(), paymentPortal);

                    break;

                case 4:
                    System.out.println("\nLogging out...");
                    logger.log(currentUserId, currentUsername, "Initiated log out process");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid option, please try again.");
                    break;
            }
        }
        scanner.close();
		System.out.println("\nExiting Program ...");
        System.out.println("Thank you for shopping with us! Come back soon!");
        logger.log(currentUserId, currentUsername, "Logged out and exited the program");
    }


    public static void viewAndAddProducts(Scanner scanner, ProductCatalog productCatalog) {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            System.out.println("\nPlease select the department you would like to shop in: ");
            System.out.println("\n1. Appliance");
            System.out.println("2. Building Materials");
            System.out.println("3. Tools");
            System.out.println("4. Go back to Main Menu\n");
            System.out.println("Enter the number of your choice: ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer
            
            // If the user wants to go back to the main menu
            if (categoryChoice == 4) {
                backToMainMenu = true;
                continue; // Allows user to go back if selected wrong choice
            }
    
            String selectedCategory = "";
            switch (categoryChoice) {
                case 1:
                    selectedCategory = "Appliance";
                    logger.log(currentUserId, currentUsername, "User chose to view products in the Appliance department");
                    break;
                case 2:
                    selectedCategory = "Building Materials";
                    logger.log(currentUserId, currentUsername, "User chose to view products in the Building Materials department");
                    break;
                case 3:
                    selectedCategory = "Tools";
                    logger.log(currentUserId, currentUsername, "User chose to view products in the Tools department");
                    break;
                default:
                    System.out.println("Invalid category selection. Please try again.");
                    continue; // Start the loop again to let the user choose a valid option
            }
    
            boolean backToDepartmentSelection = false;
            while (!backToDepartmentSelection) {
                System.out.println("\nProducts in the " + selectedCategory + " department: \n");
                for (Product product : productCatalog.getProducts()) {
                    if (product.getCategory().equalsIgnoreCase(selectedCategory)) {
                        System.out.println(product.getName() + " - $" + product.getPrice());
                    }
                }
                System.out.println("\nEnter the name of the product you wish to add or type 'back' to return to department selection.");
                
                String productName = scanner.nextLine().trim();

                // If the user wants to go back to the department selection
                if ("back".equalsIgnoreCase(productName)) {
                    backToDepartmentSelection = true; // Exit the product listing loop and show department options again
                    logger.log(currentUserId, currentUsername, "User chose to go back to department selection");
                    continue;
                }
    
                int quantity = readInt("\nHow many would you like to add?", "Please enter a number.");
    
                // Find the product by name
                final String finalSelectedCategory = selectedCategory;
                Product selectedProduct = productCatalog.getProducts().stream()
                        .filter(p -> p.getName().equalsIgnoreCase(productName) && p.getCategory().equalsIgnoreCase(finalSelectedCategory))
                        .findFirst()
                        .orElse(null);
                
                // If the product is found, add it to the cart
                if (selectedProduct != null) {
                    ShoppingCart userCart = ShoppingCart.getInstance();
                    for (int i = 0; i < quantity; i++) {
                        userCart.addItem(selectedProduct);
                    }
                    System.out.println("Added " + quantity + " x " + productName + " to your cart.");
                    logger.log(currentUserId, currentUsername, "Added " + quantity + " x " + productName + " to their cart");
                } else {
                    System.out.println("Product not found. Make sure you've entered the product name exactly as shown."); // Inform user of the error and reprompt
                    logger.log(currentUserId, currentUsername, "Incorrectly added a product name");
                }

                // Option to continue shopping in the same department or go back
                String response = askYesNo("\nWould you like to add more products from " + selectedCategory + "?");
                if ("N".equalsIgnoreCase(response) || "NO".equalsIgnoreCase(response)) {
                    backToDepartmentSelection = true; // Go back to the department selection
                    logger.log(currentUserId, currentUsername, "User chose not to add more products from " + selectedCategory);
                }
            }
        }
    }
    
    
    // method to view the cart
    private static void viewCart() {
        ShoppingCart cart = ShoppingCart.getInstance();
        if (cart.getCartSize() == 0) {
            System.out.println("Your cart is empty.");
            logger.log(currentUserId, currentUsername, "Viewed empty cart.");
            return;
        }
        
        cart.listItems();
        
        // Option to remove items from the cart
        String removeResponse = askYesNo("\nWould you like to remove any items from your cart?");
        if ("Y".equalsIgnoreCase(removeResponse) || "YES".equalsIgnoreCase(removeResponse)) {
            while (true) {
                System.out.println("Enter the name of the product you wish to remove, or type 'done' to finish:");
                logger.log(currentUserId, currentUsername, "Initiated removal of product from cart");
                String productName = scanner.nextLine().trim();
                if ("done".equalsIgnoreCase(productName)) {
                    break;
                }
                // Find the product by name
                Product productToRemove = cart.getCartItems().keySet().stream()
                    .filter(p -> p.getName().equalsIgnoreCase(productName))
                    .findFirst()
                    .orElse(null);
                // If the product is found, remove it from the cart
                if (productToRemove != null) {
                    int quantityToRemove = readInt("How many would you like to remove?", "Please enter a valid number.");
                    cart.removeItem(productToRemove, quantityToRemove);
                    System.out.println(quantityToRemove + " x " + productToRemove.getName() + " has been removed from your cart.");
                    logger.log(currentUserId, currentUsername, quantityToRemove + " x " + productToRemove.getName() + " has been removed from your cart.");
                } else {
                    System.out.println("Product not found in cart. Make sure you've entered the name exactly as listed.");
                    logger.log(currentUserId, currentUsername, "Attempted to remove product not in cart.");
                }
            }
        }
    }
    


    // method to checkout
    private static void checkout(Scanner scanner, ShoppingCart cart, PaymentPortal paymentPortal) {
        if (cart.getCartSize() == 0) {
            System.out.println("Your cart is empty. Add some products before checking out.");
            logger.log(currentUserId, currentUsername, "Attempted to checkout with an empty cart");
            return;
        }
        // Display the cart items and total amount
        double totalAmount = cart.getTotalPrice();
        System.out.println("Your total amount is: $" + totalAmount);

        // Ask the user if they want to proceed with the payment
        String proceedWithPayment = askYesNo("Do you want to proceed with the payment?");
        if (!"Y".equalsIgnoreCase(proceedWithPayment) && !"YES".equalsIgnoreCase(proceedWithPayment)) {
            System.out.println("Checkout cancelled. Returning to main menu...");
            logger.log(currentUserId, currentUsername, "Checkout process was cancelled by the user");
            return;
        }
        
        // Process the payment
        boolean paymentSuccess = paymentPortal.processPayment(totalAmount);
        if (paymentSuccess) {
            System.out.println("Thank you for your purchase!");
            logger.log(currentUserId, currentUsername, "Checkout process was succesful, payment was processed successfully");
            //clear the cart after a successful purchase
            cart.clearCart();
        } else {
            System.out.println("There was a problem with your payment. Please try again or contact support.");
            logger.log(currentUserId, currentUsername, "Payment processing failed. User was prompted to try again or contact support.");
        }
    }
    
    

}
