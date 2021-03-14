package com.litewka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static ArrayList<ArrayList<Double>> readFile() {

        ArrayList<ArrayList<Double>> list = new ArrayList<>();

        File file = new File("/Users/macbook/Desktop/dane/dane.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Double x, y;
        while (in.hasNextDouble()) {
            x = in.nextDouble();
            if (in.hasNextDouble()) {
                y = in.nextDouble();

                list.add(new ArrayList<Double>());
                list.get(list.size() - 1).add(x);
                list.get(list.size() - 1).add(y);
            }
        }
        return list;
    }

    public static ArrayList<Double> interpolacja(ArrayList<ArrayList<Double>> list) {
        ArrayList<Double> wynik = new ArrayList<>(0);
        for (int i = 0; i < list.size(); i++) {
            wynik.add(0.0);
        }


        for (int i = 0; i < list.size(); i++) {
            ArrayList<Double> wynik2 = new ArrayList<>(0);

            Double dzielenie = 1.0;
            wynik2.add(1.0);


            for (int j = 0; j < list.size(); j++) {
                if (i != j) {

                    Double x = list.get(j).get(0);

                    ArrayList<Double> wynik3 = new ArrayList<>(wynik2);
                    wynik3.add(0, 0.0);

                    wynik2.add(0.0);

                    int roz = wynik2.size();
                    for (int k = 0; k < roz; k++) {
                        wynik2.set(k, -wynik2.get(k) * x);

                        wynik2.set(k, wynik3.get(k) + wynik2.get(k));

                    }
                    dzielenie *= list.get(i).get(0) - list.get(j).get(0);
                }
            }
            for (int j = 0; j < wynik.size(); j++) {
                wynik.set(j,
                        wynik.get(j) +
                                (wynik2.get(j) * list.get(i).get(1)) / dzielenie);
            }
        }


        return wynik;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Double>> list = readFile();


        for (ArrayList<Double> i : list) {
            for (Double j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        ArrayList<Double> wynik = interpolacja(list);
        for (Double i : wynik) {
            System.out.println(i);
        }

        int[] xP = new int[11];
        int[] yP = new int[11];
        double a;
        for (int i = 0; i <= 10; i++) {
            xP[i] = i - 5;
            for (int j = 0; j < wynik.size(); j++) {
                yP[i] = (int) (Math.pow((double) xP[i], (double) j) *
                        wynik.get(j) + yP[i]);
                a = Math.pow((double) xP[i], (double) j) * wynik.get(j);
                System.out.println(a);
            }


        }
        MyFrame myFrame = new MyFrame(xP, yP);


    }
}
