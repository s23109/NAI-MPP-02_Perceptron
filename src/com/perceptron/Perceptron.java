package com.perceptron;

import java.util.List;

public class Perceptron {

    //perceptron do rozrózniania versicolor od drugiego

    final double stala_uczenia = 0.01;
    final double akceptowalny_procent_bladow = 0.02 ;
    double prog_odchylenia = 3 ;
    private List<Double> wagi ;
    private int number_of_wagi ;

    public Perceptron(int number_of_wagi) {
        this.number_of_wagi = number_of_wagi;
        System.out.print("Wagi Perceptronu: ");
        for (int i = 0; i < number_of_wagi; i++) {
            wagi.add((Math.random()-0.15)*2.0*(Math.random()+0.15>0.5?-1:1));
            System.out.print(wagi.get(i) + " ");
        }
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




}
