package com.perceptron;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //File name is hard-coded
        //"Dane\\perceptron.data.txt"

        /*
        Wariant versicolor = 0
        Wariant virginica = 1
         */
        final int learn_loop_limit = 10000;
        boolean loop_bool = true;
        int loop_iterations=0;

        final double stala_uczenia = 0.05;
        final double akceptowalny_procent_bladow = 0.02 ;
        List<Element> zbior_treningowy = File_operations.list_from_file("Dane\\perceptron.data.txt");
        List<Element> zbior_testowy = File_operations.list_from_file("Dane\\perceptron.test.data.txt");

        Perceptron perceptron = new Perceptron(zbior_treningowy.get(0).coordinates.size(),stala_uczenia,akceptowalny_procent_bladow);

        //petla nauki
        double ilosc_testowanych ;
        double ilosc_poprawnie_klasyfikowanych ;

        while (loop_bool){
            ilosc_testowanych = 0.0;
            ilosc_poprawnie_klasyfikowanych = 0.0;

            System.out.print(loop_iterations + ". ");
            perceptron.wypisz_wagi();

            for (Element e: zbior_treningowy
                 ) {
                ilosc_testowanych+=1.0;
                int guessed = perceptron.guess(e.coordinates);
                /*
                 Wariant versicolor = 0
                 Wariant virginica = 1
                */

                if ( (e.name_of_object.equals("Iris-versicolor") && guessed == 0) || e.name_of_object.equals("Iris-virginica") && guessed == 1 ) {
                    //zgadnięto element
                    ilosc_poprawnie_klasyfikowanych+=1.0;
                }
                else {
                    perceptron.learn(e.coordinates, guessed , (e.name_of_object.equals("Iris-versicolor") ? 0 : 1 ));
                }
            }

            //check if learned enough or limit
            if (loop_iterations == learn_loop_limit )  {
                loop_bool=false;
                System.out.println("Osiągnięto limit petli nauki. Margines bledu wymagany:"
                        + akceptowalny_procent_bladow
                        + " a osiągnięto  " + (double)(1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych))
                );

            }

            if ( (double)(1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych)) <= akceptowalny_procent_bladow) {
                loop_bool=false;
                System.out.println("Osiągnięto wymagany margines błędu równy " + (double)(1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych))
                + " który jest mniejszy/równy niż " + akceptowalny_procent_bladow
                );
            }

            loop_iterations+=1;
        }

    }
}
