package com.example.day03_task_01;


public class Order {
    private String customName;
    private static final double CoffeePrice = 20.0;
    private static final double DesertPrice = 30.0;
    private int amountCoffee;
    private int amountDesert;

    public Order() {
        this.customName = "DEFAULT";
        this.amountCoffee=0;
        this.amountDesert = 0;
    }

    public Order(String customName, int amountCoffee, int amountDesert) {
        this.customName = customName;
        this.amountCoffee = amountCoffee;
        this.amountDesert = amountDesert;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public static double getCoffeePrice() {
        return CoffeePrice;
    }

    public static double getDesertPrice() {
        return DesertPrice;
    }

    public int getAmountCoffee() {
        return amountCoffee;
    }

    public void setAmountCoffee(int amountCoffee) {
        this.amountCoffee = amountCoffee;
    }

    public int getAmountDesert() {
        return amountDesert;
    }

    public void setAmountDesert(int amountDesert) {
        this.amountDesert = amountDesert;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customName='" + customName + '\'' +
                ", amountCoffee=" + amountCoffee +
                ", amountDesert=" + amountDesert +
                '}';
    }
}
