package org.example.Model;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class DataBase implements IDB {
    Logger logger;
    FileHandler fh;
    XMLFormatter xml;
    BufferedWriter bwt;
    BufferedReader brt;
    BufferedWriter bwg;
    BufferedReader brg;
    Stock stock;

    public DataBase(Stock stock) throws IOException {
        this.bwt = new BufferedWriter(new FileWriter("toys.csv", true));
        this.brt = new BufferedReader(new FileReader("toys.csv"));
        this.bwg = new BufferedWriter(new FileWriter("gifts.csv", true));
        this.brg = new BufferedReader(new FileReader("gifts.csv"));
        this.stock = stock;
        loggerInit();
    }


    @Override
    public DataBase writeToys() {
        try {
            bwt.append(String.format(stock.allToysToWrite()));
            bwt.flush();
        } catch (IOException writeFile) {
            logger.log(Level.WARNING, "Ошибка записи файла");
        }
        return this;
    }

    @Override
    public DataBase readToys() {
        try {
            String line;
            while ((line = brt.readLine()) != null)
                stock.add(parsToys(line));
        } catch (IOException readFile) {
            logger.log(Level.WARNING, "Ошибка чтения файла");
        }
        return this;
    }

    @Override
    public DataBase writeGifts() {
        try {
            bwg.append(String.format(stock.allGiftsToWrite()));
            bwg.flush();
        } catch (IOException writeFile) {
            logger.log(Level.WARNING, "Ошибка записи файла");
        }
        return this;
    }

    @Override
    public DataBase readGifts() {
        try {
            String line;
            while ((line = brg.readLine()) != null)
                stock.loadWinners(parsGift(line));
        } catch (IOException readFile) {
            logger.log(Level.WARNING, "Ошибка чтения файла");
        }
        return this;
    }

    private Toy parsToys(String line) {
        try {
            String[] values = line.split(";");
            int id = Integer.parseInt(values[0]);
            String name = values[1];
            Integer amount = Integer.parseInt(values[2]);
            Float probability = Float.parseFloat(values[3]);
            return new Toy(id, name, amount, probability);
        } catch (Exception pars) {
            logger.log(Level.WARNING, "Ошибка преобразования данных");
            return null;
        }
    }

    private Gift parsGift(String line) {
        try {
            String[] values = line.split(";");
            int id = Integer.parseInt(values[0]);
            String name = values[1];
            return new Gift(id, name);
        } catch (Exception pars) {
            logger.log(Level.WARNING, "Ошибка преобразования данных");
            return null;
        }
    }

    private void loggerInit() throws IOException {
        this.logger = Logger.getLogger(DataBase.class.getName());
        this.fh = new FileHandler("logs.xml");
        logger.addHandler(fh);
        this.xml = new XMLFormatter();
        fh.setFormatter(xml);
    }

    public Logger getLogger() {
        return logger;
    }
}
