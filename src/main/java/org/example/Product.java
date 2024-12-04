package org.example;

public class Product {
    static int currentId = 1;

    final int id;
    int timeHour;
    int material;
    int profit;

    public Product(int timeHour, int material, int profit) {
        this.timeHour = timeHour;
        this.material = material;
        this.profit = profit;
        this.id = currentId;
        currentId++;
    }


}
