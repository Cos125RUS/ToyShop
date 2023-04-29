package org.example.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Stock implements IStock{
    private HashMap<Integer, Toy> allToys;
    private Queue<Gift> winners;

    public Stock() {
        this.allToys = new HashMap<>();
        this.winners = new LinkedList<>();
    }

    public HashMap<Integer, Toy> getAllToys() {
        return allToys;
    }

    @Override
    public void add(Toy newToy) {
        allToys.put(newToy.getId(), newToy);
    }

    @Override
    public void del(Integer id) {

    }

    @Override
    public void changeAmount(Integer id, Integer newAmount) {

    }

    @Override
    public void changeProbability(Integer id, Float probability) {

    }

    @Override
    public void lottery(ArrayList<Toy> win) {
        if (win.size() > 0)
            for (Toy toy: win){
                winners.add(new Gift(toy.getId(), toy.getName()));
                toy.giftIt();
            }
    }

    public Queue<Gift> getWinners() {
        return winners;
    }
}
