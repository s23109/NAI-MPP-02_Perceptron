package com.perceptron;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File_operations {

    public static List<String> create_list (String Path){

        BufferedReader treningdata = null;
        List <String> lines = new ArrayList<>();
        try {
            treningdata = new BufferedReader(new FileReader(Path));
            String line = new String();

            while ((line = treningdata.readLine()) != null){
               // System.out.println(line);
                lines.add(line);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }


}
