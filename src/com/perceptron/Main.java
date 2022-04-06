package com.perceptron;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //File name is hard-coded
        //"Dane\\perceptron.data.txt"
        /*
        Wariant versicolor = 0
        Wariant virginica = 1
         */
        boolean user_input = false;

        final int learn_loop_limit = 100;
        boolean loop_bool = true;
        int loop_iterations=0;

        final double stala_uczenia = 0.1;
        final double akceptowalny_procent_bladow = 0.025 ;
        List<Element> zbior_treningowy = File_operations.list_from_file("Dane\\perceptron.data.txt");
        List<Element> zbior_testowy = File_operations.list_from_file("Dane\\perceptron.test.data.txt");

        List<String> pomo_typy = new ArrayList<>();
        pomo_typy.add("versicolor");
        pomo_typy.add("virginica");

        Perceptron perceptron = new Perceptron(zbior_treningowy.get(0).coordinates.size(),stala_uczenia,akceptowalny_procent_bladow);

        //petla nauki



        double ilosc_testowanych ;
        double ilosc_poprawnie_klasyfikowanych ;

        while (loop_bool){
            ilosc_testowanych = 0.0;
            ilosc_poprawnie_klasyfikowanych = 0.0;

            System.out.print(loop_iterations + ". ");
            perceptron.wypisz_wagi();

            for (Element e: zbior_treningowy) {
                ilosc_testowanych+=1.0;
                //Pętla nauki + zgadywania
                //ilosc_poprawnie_klasyfikowanych+=1.0;
                int guess = perceptron.guess(e);
                /*
                 Wariant versicolor = 0
                Wariant virginica = 1
                */

                if (e.name_of_object.contains(pomo_typy.get(guess))){
                    //jeśli trafiony, to git
                    ilosc_poprawnie_klasyfikowanych+=1.0;
                }
                else {
                    perceptron.learn(e);
                }



            }


            //check if learned enough or limit
            if (loop_iterations == learn_loop_limit )  {
                loop_bool=false;
                System.out.println("-----------------------\nOsiągnięto limit petli nauki. Margines bledu wymagany:"
                        + akceptowalny_procent_bladow
                        + " a osiągnięto  " + (1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych))
                );

            }
            else {
                System.out.println("Aktualna dokładność: " + (1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych)) + "\n-----------------------");
            }
            if ( (1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych)) <= akceptowalny_procent_bladow) {
                loop_bool=false;
                System.out.println("Osiągnięto wymagany margines błędu równy " + (1.0 - (ilosc_poprawnie_klasyfikowanych/ilosc_testowanych))
                + " który jest mniejszy/równy niż " + akceptowalny_procent_bladow
                );
            }
            loop_iterations+=1;
        }

        System.out.println("\n" + (user_input ? "User input ":"Test from file ") + "detected \n") ;

        if (!user_input){
            //file input
             ilosc_testowanych=0.0;
             ilosc_poprawnie_klasyfikowanych=0.0;

                /*
                Wariant versicolor = 0
                Wariant virginica = 1
                */

            for (Element testowy: zbior_testowy
                 ) {

                int guess = perceptron.guess(testowy);

                if (testowy.name_of_object.contains(pomo_typy.get(guess))){
                    //poprawnie trafiony
                    System.out.println("Poprawnie : Strzelano " + (guess==1? "Wariant virginica " : "Wariant versicolor "));
                    ilosc_poprawnie_klasyfikowanych+=1.0;
                }
                else {
                    System.out.println("Źle : Strzelano " + (guess==1? "Wariant virginica " : "Wariant versicolor ") + " Rzeczywiste : " + testowy.name_of_object);
                }
                ilosc_testowanych+=1.0;

            }
            System.out.println("-----------------------\nStosunek trafionych do nietrafionych | " + (int ) ilosc_poprawnie_klasyfikowanych + " : " + (int)(ilosc_testowanych-ilosc_poprawnie_klasyfikowanych) );
            System.out.println("Procent poprawnie trafionych : " + ilosc_poprawnie_klasyfikowanych/ilosc_testowanych);
            perceptron.wypisz_wagi();
        }
        else {
            //user input placeholder
            System.out.println("Aktualna ,,szerokość'' perceptronu :" + perceptron.number_of_wagi + "\n");


        }


    }
}
