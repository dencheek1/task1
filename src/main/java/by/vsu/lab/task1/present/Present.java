package by.vsu.lab.task1.present;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import by.vsu.lab.task1.filters.Filter;
import by.vsu.lab.task1.sweet.Sweet;

public class Present implements Serializable{

    
    private float weight;
    private ArrayList<Sweet> sweets;

    public Present() {
	sweets = new ArrayList<Sweet>();
	weight = 0;
    }

    public Present(ArrayList<Sweet> sweets) {
	this.sweets = sweets;
	weight = 0;
	for(Sweet sweet: sweets) {
	    weight += sweet.getWeight();
	}
    }

    public float getWeight() {
	return weight;
    }

    public void sort(Comparator<Sweet> compare) {
	sweets.sort(compare);
    }

    public void addSweet(Sweet sweet) {
	sweets.add(sweet);
	weight += sweet.getWeight();
    }

    public String toString() {
	String ret = new String();
	for(Sweet sweet: sweets) {
	    ret += sweet.toString() + "\n";
	}
	return ret;
    }

    public ArrayList<Sweet> find(Filter filter) {
	ArrayList<Sweet> result = new ArrayList<Sweet>();

	for(Sweet sweet: sweets) {
	    if(filter.check(sweet)) result.add(sweet);
	}
	return result;
    }
}
