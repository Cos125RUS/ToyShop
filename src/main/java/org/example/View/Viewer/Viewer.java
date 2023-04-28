package org.example.View.Viewer;

import java.util.Scanner;

public class Viewer implements IPrompt, IShow {
    private StringBuilder sb;
    private Scanner scan;
    private String[] view;

    public Viewer() {
        this.sb = new StringBuilder();
        this.scan = new Scanner(System.in);
        this.view = allView();
    }


    private String[] allView() {
        String[] positionsView = {menu(), positions(), newItem(), amount(), probability(),
                lottery(), takePrize(), exit()};
        return positionsView;
    }

    private String menu() {
        sb.append("\nГлавное меню: \n");
        sb.append("1.Посмотреть список позиций\n");
        sb.append("2.Добавить позицию\n");
        sb.append("3.Изменить количество товара\n");
        sb.append("4.Изменить вес (частоту выпадения)\n");
        sb.append("5.Запустить розыгрыш\n");
        sb.append("6.Выдать приз\n");
        sb.append("9.Выйти\n");
        return sb.toString();
    }

    private String positions() {
        return "\nАссортимент игрушек в магазине:\n" +
            "id\tНазвание\tКоличество\t\tВероятность выигрыша";
    }

    private String newItem() {
        return "\nДобавление новой игрушки в каталог товаров\n";
    }

    private String probability() {
        return "\nИзменение вероятности выигрыша\n";
    }

    private String amount() {
        return "\nИзменение количества товара на складе\n";
    }

    private String lottery() {
        return "\nРозыгрыш игрушек";
    }

    private String takePrize() {
        return "\nВыдача призов";
    }

    private String exit() { return "\nДо новых встреч!"; }

    @Override
    public void show(String text) {
        System.out.println(text);
    }

    @Override
    public void inputError() {
        System.out.println("\nОшибка ввода");
    }

    @Override
    public void changeComplete() {
        System.out.println("\nИзменения внесены");
    }

    @Override
    public Integer inputInt(String text) {
        System.out.print(text);
        try {
            return scan.nextInt();
        } catch (Exception e) {
            scan = new Scanner(System.in);
            return null;
        }
    }

    @Override
    public String inputStr(String text) {
        System.out.print(text);
        return scan.next();
    }

    @Override
    public Float inputFloat(String text) {
        System.out.print(text);
        try {
            return scan.nextFloat();
        } catch (Exception e) {
            scan = new Scanner(System.in);
            return null;
        }
    }

    public String[] getView() {
        return view;
    }
}
