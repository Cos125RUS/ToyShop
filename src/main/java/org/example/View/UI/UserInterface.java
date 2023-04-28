package org.example.View.UI;

import org.example.Model.Toy;
import org.example.View.Viewer.Viewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UserInterface implements IUserAction, IUserInfo {
    private final Viewer viewer;
    private final String[] listsMenu;

    public UserInterface() {
        this.viewer = new Viewer();
        this.listsMenu = viewer.getView();
    }

    @Override
    public void showMenu(int index) {
        viewer.show(listsMenu[index]);
    }

    @Override
    public void newItem() {
        viewer.show("\nПозиция добавлена");
    }

    @Override
    public Integer choiceMenu() {
        Integer index = 0;
        try {
            index = viewer.inputInt("Выберите пункт меню: ");
        } catch (Exception choiceMenu) {
            viewer.inputError();
        }
        if (index == 9)
            index = 7;
        return index;
    }

    @Override
    public void showPositions(HashMap<Integer, Toy> allToys) {
        for (Map.Entry<Integer, Toy> toy : allToys.entrySet()) {
            viewer.show(toy.getValue().toString());
        }
    }

    @Override
    public Toy addItem() throws InterruptedException {
        try {
            String name = viewer.inputStr("Название игрушки: ");
            return new Toy(name, enterAmount(), enterProbability());
        } catch (Exception addItem) {
            viewer.inputError();
            TimeUnit.SECONDS.sleep(1);
            return null;
        }
    }

    @Override
    public void amount(HashMap<Integer, Toy> allToys) throws InterruptedException {
        showPositions(allToys);
        Integer findId = findById(allToys.keySet());
        Integer newAmount = enterAmount();
        allToys.get(findId).setAmount(newAmount);
        viewer.changeComplete();
    }

    @Override
    public Float probability() {
        return null;
    }

    @Override
    public Toy lottery(ArrayList<Toy> allToys) {
        return null;
    }

    @Override
    public void takePrize(Toy gift) {

    }

    @Override
    public void pressEnter() {
        viewer.inputStr("\nНажмите Enter для выхода");
    }

    @Override
    public void error() {
        viewer.inputError();
    }

    @Override
    public void fail() {
        viewer.show("\nПозиция не создана");
    }

    private boolean checkId(Integer checkingId, Set<Integer> toysId) {
        for (Integer id : toysId) {
            if (checkingId.equals(id))
                return true;
        }
        return false;
    }

    private Integer findById(Set<Integer> allId) throws InterruptedException {
        Integer findId = -1;
        boolean check = true;
        do {
            try {
                findId = viewer.inputInt("Укажите id игрушки: ");
                if (findId < 0) {
                    viewer.show("id не может быть меньше 0");
                    check = false;
                }
                if (!checkId(findId, allId)) {
                    viewer.show("id не найден");
                    check = false;
                }
            } catch (Exception amount) {
                viewer.inputError();
                TimeUnit.SECONDS.sleep(1);
            }
        } while (!check);
        return findId;
    }

    private Integer enterAmount() throws InterruptedException {
        Integer amount = -1;
        do {
            try {
                amount = viewer.inputInt("Объём партии: ");
                if (amount < 0) viewer.show("Объём не может быть меньше 0");
            } catch (Exception enterAmount) {
                viewer.inputError();
                TimeUnit.SECONDS.sleep(1);
            }
        } while (amount < 0);
        return amount;
    }

    private Float enterProbability() throws InterruptedException {
        Integer probability = -1;
        do {
            try {
                probability = viewer.inputInt("Вероятность выигрыша в лотерее (0-100%): ");
                if (probability < 0) viewer.show("Вероятность не может быть меньше 0");
                if (probability > 100) viewer.show("Вероятность не может быть больше 100");
            } catch (Exception enterProbability) {
                viewer.inputError();
                TimeUnit.SECONDS.sleep(1);
            }
        } while (probability < 0 || probability > 100);
        return probability.floatValue() / 100;
    }
}
