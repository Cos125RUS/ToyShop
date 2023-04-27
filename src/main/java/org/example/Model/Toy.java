package org.example.Model;

public class Toy {
    int id;
    String name;
    int amount;
    float probability;
    private static int id_count = 0;

    public Toy(String name, int amount, float probability) {
        this.id = id_count++;
        this.name = name;
        this.amount = amount;
        this.probability = probability;
    }
}
