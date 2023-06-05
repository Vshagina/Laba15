package com.company;
import java.io.*;

public class Laba15 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите значение x: ");
            int x = Integer.parseInt(br.readLine());
            Calculate y1 = new Calculate(x);
            System.out.println("y = " + y1.getY());
            System.out.println("Введите команду (save/upload): ");
            String command = br.readLine();
            if (command.equals("save")) {
                Calculate.saveY(y1);
            } else if (command.equals("upload")) {
                Calculate upload_Y = Calculate.uploadY();
                if (upload_Y  != null) {
                    System.out.println("Восстановленное значение y = " + upload_Y.getY());
                }
            }
        } catch (Exception ex) {
            System.out.println("Ошибка ввода: " + ex.getMessage());
        }
    }
}

class Calculate implements Serializable {

    private int x;
    private double y;

    public Calculate(int x) {
        this.x = x;
        this.y = x - Math.sin(x);
    }

    public double getY() {
        return y;
    }

    public static void saveY(Calculate y) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Answer.txt"))) {
            System.out.println("Сохранено успешно!");
            oos.writeObject(y);
        } catch (Exception ex) {
            System.out.println("Ошибка сохранения: " + ex.getMessage());
        }
    }

    public static Calculate uploadY() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Answer.txt"))) {
            System.out.println("Загружено успешно!");
            Calculate upload_Y = (Calculate) ois.readObject();
            return upload_Y;
        } catch (Exception ex) {
            System.out.println("Ошибка загрузки: " + ex.getMessage());
            return null;
        }
    }
}