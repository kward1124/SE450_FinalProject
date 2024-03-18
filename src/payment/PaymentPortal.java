package finalproject;

public class PaymentPortal {
    private PaymentProcessor paymentProcessor;

    public PaymentPortal(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public boolean processPayment(double amount) {
        String transactionId = paymentProcessor.initiatePayment(amount);
        System.out.println("Processing payment, transaction ID: " + transactionId);
        
        PaymentStatus status = paymentProcessor.checkPaymentStatus(transactionId);
        if (status == PaymentStatus.SUCCESS) {
            System.out.println("Payment completed successfully.");
            return true;
        } else {
            System.out.println("Payment failed with status: " + status);
            return false;
        }
    }
}