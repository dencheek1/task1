package by.vsu.lab.task1.comparators;

import java.util.Comparator;

import by.vsu.lab.task1.sweet.Sweet;

public class SweetNameComparator implements Comparator<Sweet> {
    
    @Override
    public int compare(Sweet sweet0, Sweet sweet1) {

	return sweet0.getName().compareTo(sweet1.getName());
    }
}
