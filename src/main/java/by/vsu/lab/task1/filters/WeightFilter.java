package by.vsu.lab.task1.filters;

import by.vsu.lab.task1.sweet.Sweet;

public class WeightFilter implements Filter {

    private float weightFrom;
    private float weightTo;

    public WeightFilter(float weightFrom,float weightTo) {
	this.weightFrom = weightFrom;
	this.weightTo = weightTo;
    }

    @Override
    public boolean check(Sweet sweet) {
	
	return sweet.getWeight() >=weightFrom && sweet.getWeight() <= weightTo;
    }

}
