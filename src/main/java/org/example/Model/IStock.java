package org.example.Model;

import java.util.ArrayList;

public interface IStock {
    public void add(Toy newToy);
    public void del(Integer id);
    public void changeAmount(Integer id, Integer newAmount);
    public void changeProbability(Integer id, Float probability);
    public void lottery(ArrayList<Toy> win);
    public String allToysToWrite();
    public String allGiftsToWrite();
    public void loadWinners(Gift gift);
}
