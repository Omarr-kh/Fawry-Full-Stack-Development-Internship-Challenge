import model.*;
import cart.*;
import customer.Customer;
import order.CheckoutService;
import util.ShippingService;
import util.ShippableItem;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cheese cheese = new Cheese("Cheese", 100, 10, 0.2, false);
        Biscuit biscuit = new Biscuit("Biscuit", 150, 5, 0.7, false);
        TV tv = new TV("TV", 200, 3, 7.0);
        Mobile mobile = new Mobile("Mobile", 300, 2, 0.5);
        ScratchCard scratchCard = new ScratchCard("Scratch Card", 50, 100);
        Cheese expiredCheese = new Cheese("Expired Cheese", 100, 10, 0.2, true);

        Customer customer = new Customer("Omar Khaled", 7000);
        Cart cart = new Cart();

        cart.add(cheese, 2);
        cart.add(biscuit, 1);
        cart.add(tv, 1);
        cart.add(mobile, 1);
        cart.add(scratchCard, 1);

        List<ShippableItem> shippableItems = new ArrayList<>();
        for (cart.CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.requiresShipping()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add(new ShippableItem() {
                        public String getName() { return product.getName(); }
                        public double getWeight() { return product.getWeight(); }
                    });
                }
            }
        }
        System.out.println("\n--- ShippingService ---");
        ShippingService.ship(shippableItems);
        System.out.println("---------------------------\n");

        System.out.println();
        System.out.println("----------------------\nExample 1");
        CheckoutService.checkout(customer, cart);

        System.out.println();
        System.out.println("----------------------\nExample 2");
        Cart emptyCart = new Cart();
        CheckoutService.checkout(customer, emptyCart);

        System.out.println();
        System.out.println("----------------------\nExample 3");
        Customer poorCustomer = new Customer("Poor Customer", 100);
        Cart expensiveCart = new Cart();
        expensiveCart.add(tv, 2);
        CheckoutService.checkout(poorCustomer, expensiveCart);

        System.out.println();
        System.out.println("----------------------\nExample 4");
        Cart expiredCart = new Cart();
        expiredCart.add(expiredCheese, 1);
        CheckoutService.checkout(customer, expiredCart);

        System.out.println();
        System.out.println("----------------------\nExample 5");
        Customer customer5 = new Customer("ammar", 9000);
        Cart cart5 = new Cart();
        cart5.add(biscuit, 5);
        CheckoutService.checkout(customer5, cart5);
    }
}
