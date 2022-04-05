package com.perceptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //File name is hard-coded
        //"Dane\\perceptron.data.txt"

        /*
        Wariant versicolor = 0
        Wariant virginica = 1
         */
        final int learn_loop_limit = 250;
        final double stala_uczenia = 0.01;
        final double akceptowalny_procent_bladow = 0.02 ;
        List<Element> zbior_treningowy = File_operations.list_from_file("Dane\\perceptron.data.txt");
        List<Element> zbior_testowy = File_operations.list_from_file("Dane\\perceptron.test.data.txt");

        Perceptron perceptron = new Perceptron(zbior_treningowy.get(0).coordinates.size(),stala_uczenia,akceptowalny_procent_bladow);




    }
}
