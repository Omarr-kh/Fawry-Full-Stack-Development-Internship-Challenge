package model;

public class ScratchCard extends Product {
    public ScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    public boolean requiresShipping() { return false; }
    public double getWeight() { return 0; }
    public boolean isExpired() { return false; }
} 