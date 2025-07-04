package model;

public class Biscuit extends Product implements Expirable, Shippable {
    private double weight;
    private boolean expired;
    public Biscuit(String name, double price, int quantity, double weight, boolean expired) {
        super(name, price, quantity);
        this.weight = weight;
        this.expired = expired;
    }
    public boolean requiresShipping() { return true; }
    public double getWeight() { return weight; }
    public boolean isExpired() { return expired; }
} 