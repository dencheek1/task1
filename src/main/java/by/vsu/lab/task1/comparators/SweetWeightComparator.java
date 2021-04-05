package by.vsu.lab.task1.comparators;

import java.util.Comparator;

import by.vsu.lab.task1.sweet.Sweet;

public class SweetWeightComparator implements Comparator<Sweet> {

    @Override
    public int compare(Sweet sweet0, Sweet sweet1) {
	if(sweet0.getWeight() > sweet1.getWeight())return 1;
	else if(sweet0.getWeight() < sweet1.getWeight()) return -1;
	return 0;
    }

}
