package model;

public class Mobile extends Product implements Shippable {
    private double weight;
    public Mobile(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    public boolean requiresShipping() { return true; }
    public double getWeight() { return weight; }
    public boolean isExpired() { return false; }
} 