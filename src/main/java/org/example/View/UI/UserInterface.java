package org.example.View.UI;

import org.example.Model.Gift;
import org.example.Model.Toy;
import org.example.View.Viewer.Viewer;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserInterface implements IUserAction, IUserInfo {
    Logger logger;
    private final Viewer viewer;
    private final String[] listsMenu;

    public UserInterface(Logger logger) {
        this.logger = logger;
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
        logger.info("Позиция добавлена");
    }

    @Override
    public Integer choiceMenu() {
        Integer index = 0;
        try {
            index = viewer.inputInt("Выберите пункт меню: ");
        } catch (Exception choiceMenu) {
            viewer.inputError();
            logger.log(Level.WARNING, "Ошибка ввода данных");
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
        if (allToys.size() == 0) viewer.show("\nСписок товаров пуст\n");
    }

    @Override
    public Toy addItem() throws InterruptedException {
        try {
            String name = viewer.inputStr("Название игрушки: ");
            return new Toy(name, enterAmount(), enterProbability());
        } catch (Exception addItem) {
            viewer.inputError();
            TimeUnit.SECONDS.sleep(1);
            logger.log(Level.WARNING, "Ошибка ввода данных");
            return null;
        }
    }

    @Override
    public void amount(HashMap<Integer, Toy> allToys) throws InterruptedException {
        Integer findId = fundId(allToys);
        Integer newAmount = enterAmount();
        allToys.get(findId).setAmount(newAmount);
        viewer.changeComplete();
        logger.info("Изменение количества игрушек");
    }

    @Override
    public void probability(HashMap<Integer, Toy> allToys) throws InterruptedException {
        Integer findId = fundId(allToys);
        Float newProbability = enterProbability();
        allToys.get(findId).setProbability(newProbability);
        viewer.changeComplete();
        logger.info("Изменение вероятности выигрыша");
    }

    @Override
    public ArrayList<Toy> lottery(HashMap<Integer, Toy> allToys) {
        Random rand = new Random();
        ArrayList<Toy> winners = new ArrayList<>();
        for (Toy toy : allToys.values())
            if (rand.nextFloat() <= toy.getProbability())
                winners.add(toy);
        if (winners.size() > 0) {
            for (Toy toy : winners) {
                viewer.show(String.format("\nРозыгран приз: %s", toy.getName()));
                logger.info("Приз разыгран");
            }
        } else {
            viewer.show("\nПобедило казино... магазин");
            logger.info("Нет выигрыша");
        }
        pressEnter();
        return winners;
    }

    @Override
    public void takePrize(Queue<Gift> gift) {
        if (gift.size() > 0) {
            for (Gift next : gift)
                viewer.show(next.toString());
            String choice;
            do {
                choice = viewer.inputStr("\nВыдать игрушку счастливому ребёнку (y/n)?) ");
                if (choice.equals("y")) {
                    Gift toy = gift.poll();
                    viewer.show(String.format("\n%s выдан визжащему от восторга мелкому паганцу",
                            toy.toString()));
                    writeFile(toy);
                    logger.info("Игрушка выдана");
                }
                if (choice.equals("n"))
                    viewer.show("\nДа, пусть подождут до Нового года");
                if (!choice.equals("y") && !choice.equals("n"))
                    viewer.show("\nЧто ты такое ввёл? Всего две буквы! 'y' или 'n'" );
            } while (!choice.equals("n"));
        }
        else viewer.show("\nТак ведь раздавать-то нечего =(");
    }

    @Override
    public void pressEnter() {
        viewer.inputStr("\nВведите 'q' для выхода ");
    }

    @Override
    public void error() {
        viewer.inputError();
        logger.log(Level.WARNING, "Ошибка ввода данных");
    }

    @Override
    public void fail() {
        viewer.show("\nПозиция не создана");
        logger.log(Level.WARNING, "Ошибка при создании новой позиции");
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
                findId = viewer.inputInt("\nУкажите id игрушки: ");
                if (findId < 0) {
                    viewer.show("\nid не может быть меньше 0");
                    check = false;
                }
                if (!checkId(findId, allId)) {
                    viewer.show("\nid не найден");
                    check = false;
                }
            } catch (Exception amount) {
                viewer.inputError();
                TimeUnit.SECONDS.sleep(1);
                logger.log(Level.WARNING, "Ошибка ввода данных");
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
                logger.log(Level.WARNING, "Ошибка ввода данных");
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
                logger.log(Level.WARNING, "Ошибка ввода данных");
            }
        } while (probability < 0 || probability > 100);
        return probability.floatValue() / 100;
    }

    private Integer fundId(HashMap<Integer, Toy> allToys) throws InterruptedException {
        showPositions(allToys);
        return findById(allToys.keySet());
    }

    private void writeFile(Gift gift) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("win.txt", true));
            bw.append(String.format("Выдан %s", gift.toString())).append("\n");
            bw.flush();
        }
        catch (IOException writeFile){
            logger.log(Level.WARNING, "Ошибка записи файла");
        }
    }
}
