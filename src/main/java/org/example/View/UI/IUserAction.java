package org.example.View.UI;

import org.example.Model.Toy;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUserAction {
    public Integer choiceMenu() ;
    public void showPositions(HashMap<Integer, Toy> allToys);
    public Toy addItem() throws InterruptedException;
    public void amount(HashMap<Integer, Toy> allToys) throws InterruptedException;
    public Float probability();
    public Toy lottery(ArrayList<Toy> allToys);
    public void takePrize(Toy gift);
    public void pressEnter();
}
