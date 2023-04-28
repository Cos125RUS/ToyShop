package org.example.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Stock implements IStock{
    private HashMap<Integer, Toy> allToys;
    private Queue<Toy> winner;

    public Stock() {
        this.allToys = new HashMap<>();
        this.winner = new LinkedList<>();
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
}
