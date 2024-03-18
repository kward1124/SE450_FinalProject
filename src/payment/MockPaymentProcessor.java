package finalproject;

import java.util.Random;

public class MockPaymentProcessor implements PaymentProcessor {
    private Random random = new Random();

    @Override
    public String initiatePayment(double amount) {
        // Generate a unique transaction ID for each payment initiation
        // Using the current time in milliseconds as a unique identifier
        String transactionId = "TRANS" + System.currentTimeMillis();
        System.out.println("Payment initiated for amount: $" + amount + ". Transaction ID: " + transactionId);
        return transactionId;
    }

    @Override
    public PaymentStatus checkPaymentStatus(String transactionId) {
        // Using a weighted random number to simulate different payment statuses
        System.out.println("Checking payment status for transaction ID: " + transactionId);
        return weightedRandomPaymentStatus(); 
    }

    @Override
    public boolean cancelPayment(String transactionId) {
        // Assuming if cancelation is requested, it is always successful
        System.out.println("Cancellation always successful for transaction ID: " + transactionId);
        return true; 
    }

    // Method to generate a weighted random payment status; in real life, this would be determined by the payment gateway
    // In this implementation, we use the random number generator to simulate the possibility of different payment statuses
    private PaymentStatus weightedRandomPaymentStatus() {
        int weight = random.nextInt(100); // Generate a number between 0 and 99
        if (weight < 92) { // Adjust weights for each step to change chance
            return PaymentStatus.SUCCESS; // 92% chance of SUCCESS
        } else if (weight < 97 ) { 
            return PaymentStatus.PENDING; // 5% chance of PENDING
        } else { 
            return PaymentStatus.FAILED; // 15% chance of FAILED
        }
    }
}
