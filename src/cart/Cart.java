package cart;

import java.util.ArrayList;
import java.util.List;
import model.Product;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    public void add(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }
    public void remove(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }
    public List<CartItem> getItems() {
        return items;
    }
    public void clear() {
        items.clear();
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }
        return subtotal;
    }
    public double getShippingFees() {
        double totalWeight = 0;
        for (CartItem item : items) {
            if (item.getProduct().requiresShipping()) {
                totalWeight += item.getProduct().getWeight() * item.getQuantity();
            }
        }
        return totalWeight * 30;
    }
    public List<Product> getShippableItems() {
        List<Product> shippable = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct().requiresShipping()) {
                shippable.add(item.getProduct());
            }
        }
        return shippable;
    }
    public int getQuantityOfProduct(Product p) {
        for (CartItem item : items) {
            if (item.getProduct().equals(p)) {
                return item.getQuantity();
            }
        }
        return 0;
    }
    public void printItems() {
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)(item.getProduct().getPrice() * item.getQuantity()));
        }
    }
}
