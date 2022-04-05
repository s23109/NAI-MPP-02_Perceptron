package com.perceptron;

import java.util.List;

public class Perceptron {

    /*
        Wariant versicolor = 0
        Wariant virginica = 1
    */

    final double stala_uczenia ;
    final double akceptowalny_procent_bladow ;
    double prog_odchylenia = 3 ;
    private List<Double> wagi ;
    private int number_of_wagi ;

    public Perceptron(int number_of_wagi , double stala_uczenia , double procent_bledow) {
        this.number_of_wagi = number_of_wagi;
        System.out.print("Wagi Perceptronu: ");
        for (int i = 0; i < number_of_wagi; i++) {
            wagi.add((Math.random()-0.15)*2.0*(Math.random()+0.15>0.5?-1:1));
            System.out.print(wagi.get(i) + " ");
        }
        this.stala_uczenia=stala_uczenia;
        this.akceptowalny_procent_bladow=procent_bledow;
        System.out.println("------------\nPerceptron created");
    }

    public void wypisz_wagi (){
        System.out.print("Aktualne wagi");
        for (Double e: wagi
             ) {
            System.out.print(e + " ");
        }

        System.out.print("\nProg odchylenia: "+ prog_odchylenia + "\n");
    }

    private void learn (List<Double> tested , int szacowany , int prawdziwy ) {

            List<Double> nowa_waga = null;

            for (int i = 0; i < tested.size(); i++) {

                nowa_waga.add(wagi.get(i)+(stala_uczenia*(prawdziwy-szacowany))*tested.get(i));

            }

            this.wagi = nowa_waga;
            this.prog_odchylenia= prog_odchylenia-stala_uczenia*(prawdziwy-szacowany);

    }

    private int guess (List<Double> tested){
        /*
        Wariant versicolor = 0
        Wariant virginica = 1
         */
        Double net = 0.0;

        for (int i = 0; i < tested.size(); i++) {
            net+=tested.get(i)*wagi.get(i);
        }

        net+=prog_odchylenia;

        if (net >= 0) {
            //virginica
            return 1;
        }
        //versicolor
        return 0;
    }




}
