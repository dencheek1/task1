package by.vsu.lab.task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.text.SimpleAttributeSet;

import by.vsu.lab.task1.comparators.SweetNameComparator;
import by.vsu.lab.task1.comparators.SweetWeightComparator;
import by.vsu.lab.task1.filters.Filter;
import by.vsu.lab.task1.filters.NameFilter;
import by.vsu.lab.task1.filters.SugarFilter;
import by.vsu.lab.task1.present.Present;
import by.vsu.lab.task1.present.PresentBuilder;
import by.vsu.lab.task1.present.SimplePresentBuilder;
import by.vsu.lab.task1.sweet.Candy;
import by.vsu.lab.task1.sweet.Cookies;
import by.vsu.lab.task1.sweet.Doughnut;
import by.vsu.lab.task1.sweet.Sweet;
import by.vsu.lab.task1.sweet.CandyTypes.CandyTypes;

public class Runner {

    public static void main(String[] args) {

	Present present;

	PresentBuilder builder = new SimplePresentBuilder();
	
	present = builder.build();
	
	System.out.println(present);

	Filter filter = new SugarFilter(20,40);

	ArrayList<Sweet> sweets = present.find(filter);

	for(Sweet sweet: sweets) {
	    System.out.println(sweet);
	}
    }

}
