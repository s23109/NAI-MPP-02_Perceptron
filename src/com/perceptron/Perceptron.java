package com.perceptron;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    /*
        Wariant versicolor = 0
        Wariant virginica = 1
    */

    final double stala_uczenia;
    final double akceptowalny_procent_bladow;
    private double Teta;
    private List<Double> wagi = new ArrayList<>();
    public int number_of_wagi;

    public Perceptron(int number_of_wagi, double stala_uczenia, double procent_bledow) {
        this.number_of_wagi = number_of_wagi;
        System.out.print("Wagi Perceptronu: ");
        for (int i = 0; i < number_of_wagi; i++) {
            wagi.add((Math.random() - 0.15) * 2.0 * (Math.random() + 0.15 > 0.5 ? -1 : 1));
            System.out.print(wagi.get(i) + " ");
        }
        this.stala_uczenia = stala_uczenia;
        this.akceptowalny_procent_bladow = procent_bledow;
        this.Teta = Math.random() * 2;
        System.out.println("\nPerceptron created\n------------");
    }

    public void wypisz_wagi() {
        System.out.print("Wagi : ");
        for (Double e : wagi
        ) {
            System.out.print(e + " ");
        }

        System.out.print("\nProg odchylenia: " + Teta + "\n");
    }

    public void learn(Element e) {
        int d = (e.name_of_object.contains("versicolor")?0:1);  //oczekiwana
        int y = guess(e);       //aktualnie szacowana

        for (int i = 0; i < wagi.size(); i++) {
            wagi.set(i,(wagi.get(i)+(d-y)*stala_uczenia*e.coordinates.get(i)));
        }


        this.Teta= (Teta-(d-y)*stala_uczenia);
    }

    private double obliczNet(Element element) {
        double net = 0;
        for (int i = 0; i < wagi.size(); i++) {
            net += wagi.get(i) * element.getCoordinates().get(i);
        }
        return (net-this.Teta);
    }

    private boolean funkcja_aktywacji (Double net){
        return (net >= 0);
    }

    public int guess(Element element) {
        /*
        Wariant versicolor = 0
        Wariant virginica = 1
         */
        if (funkcja_aktywacji(obliczNet(element))) {
            return 1;
        }
        return 0;
    }

}
