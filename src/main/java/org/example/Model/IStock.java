package org.example.Model;

public interface IStock {
    public void add(Toy newToy);
    public void del(Integer id);
    public void changeAmount(Integer id, Integer newAmount);
    public void changeProbability(Integer id, Float probability);
}
