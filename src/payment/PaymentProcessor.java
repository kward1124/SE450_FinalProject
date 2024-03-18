package finalproject;

public interface PaymentProcessor {
    String initiatePayment(double amount);
    PaymentStatus checkPaymentStatus(String transactionId);
    boolean cancelPayment(String transactionId);
}
