package org.example.Model;

public class Toy implements IToy{
    int id;
    String name;
    Integer amount;
    Float probability;
    private static int id_count = 0;

    public Toy(String name, Integer amount, Float probability) {
        this.id = id_count++;
        this.name = name;
        this.amount = amount;
        this.probability = probability;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    @Override
    public void buyIt() {
        this.amount--;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t\t" + amount + "\t\t\t\t" + probability;
    }

    public int getId() {
        return id;
    }
}
