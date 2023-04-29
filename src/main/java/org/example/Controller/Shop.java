package org.example.Controller;

import org.example.Model.Stock;
import org.example.Model.Toy;
import org.example.View.UI.UserInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Shop implements IShop {
//    private ArrayList<Toy> allToys;
    private Stock stock;
    private final UserInterface ui;
    private Boolean open;
    private Integer choiceMenu;

    public Shop() {
        this.stock = new Stock();
        this.ui = new UserInterface();
        this.open = true;
        this.choiceMenu = 0;
    }

    @Override
    public void run() throws InterruptedException {
        while (open) {
            ui.showMenu(choiceMenu);
            switch (choiceMenu) {
                case 0:
                    choiceMenu = ui.choiceMenu();
                    break;
                case 1:
                    ui.showPositions(stock.getAllToys());
                    ui.pressEnter();
                    choiceMenu = 0;
                    break;
                case 2:
                    Toy newToy = ui.addItem();
                    if (newToy != null) {
                        stock.add(newToy);
                        ui.newItem();
                    } else
                        ui.fail();
                    TimeUnit.SECONDS.sleep(1);
                    choiceMenu = 0;
                    break;
                case 3:
                    ui.amount(stock.getAllToys());
                    TimeUnit.SECONDS.sleep(1);
                    choiceMenu = 0;
                    break;
                case 4:
                    ui.probability(stock.getAllToys());
                    TimeUnit.SECONDS.sleep(1);
                    choiceMenu = 0;
                    break;
                case 5:
                    ArrayList<Toy> win = ui.lottery(stock.getAllToys());
                    stock.lottery(win);
                    TimeUnit.SECONDS.sleep(1);
                    choiceMenu = 0;
                    break;
                case 6:
                    ui.takePrize(stock.getWinners());
                    TimeUnit.SECONDS.sleep(1);
                    choiceMenu = 0;
                    break;
                case 7:
                    TimeUnit.SECONDS.sleep(1);
                    stop();
                    break;
                default:
                    ui.error();
                    choiceMenu = 0;
            }
        }
    }

    @Override
    public void stop() {
        open = false;
    }
}
