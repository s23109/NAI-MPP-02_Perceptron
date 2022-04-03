package com.perceptron;

import java.util.ArrayList;
import java.util.List;

public class Element {

    List<Double> coordinates;
    String name_of_object;

    public Element(String name_of_object, List<Double> coordinates) {
        this.name_of_object = name_of_object;
        this.coordinates = coordinates;
    }

    public Element (String[] line){
        // -1 bo ostatni ele to nazwa
        List<Double> coor_temp = new ArrayList<>();
        for (int i = 0; i < line.length-1; i++) {
            coor_temp.add(Double.valueOf(line[i]));
        }
        this.coordinates=coor_temp;
        this.name_of_object=line[line.length-1];
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

}
