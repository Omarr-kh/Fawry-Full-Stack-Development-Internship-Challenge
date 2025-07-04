package order;

import cart.Cart;
import cart.CartItem;
import model.Product;
import customer.Customer;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) {
                System.out.println("Error: Product " + item.getProduct().getName() + " is expired.");
                return;
            }
            if (item.getQuantity() > item.getProduct().getQuantity()) {
                System.out.println("Error: Not enough quantity for product " + item.getProduct().getName() + ". Requested: " + item.getQuantity() + ", Available: " + item.getProduct().getQuantity());
                return;
            }
        }
        double subtotal = cart.getSubtotal();
        double shippingFees = cart.getShippingFees();
        double totalAmount = subtotal + shippingFees;
        if (customer.getBalance() < totalAmount) {
            System.out.println("Error: Insufficient balance.");
            return;
        }
        if (!cart.getShippableItems().isEmpty()) {
            System.out.println("** Shipment notice **");
            double totalWeight = 0;
            for (Product product : cart.getShippableItems()) {
                double productWeight = product.getWeight();
                int quantity = cart.getQuantityOfProduct(product);
                System.out.println(quantity + "x " + product.getName() + " " + (int)(productWeight * quantity * 1000) + "g");
                totalWeight += productWeight * quantity;
            }
            System.out.println("Total package weight: " + String.format("%.1f", totalWeight) + "kg");
        }
        customer.deductBalance(totalAmount);
        for (CartItem item : cart.getItems()) {
            int newQty = item.getProduct().getQuantity() - item.getQuantity();
            item.getProduct().setQuantity(newQty);
        }
        System.out.println("---------------------- ");
        System.out.println("** Checkout receipt **");
        cart.printItems();
        System.out.println("---------------------- ");
        System.out.println("Subtotal: " + (int)subtotal);
        System.out.println("Shipping: " + (int)shippingFees);
        System.out.println("Amount: " + (int)totalAmount);
        System.out.println("Customer balance after payment: " + (int)customer.getBalance());
    }
} 