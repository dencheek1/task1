package by.vsu.lab.task1.filters;

import by.vsu.lab.task1.sweet.Sweet;

public class SugarFilter implements Filter {

    private float sugarFrom;
    private float sugarTo;

    public SugarFilter(float sugarFrom, float sugarTo) {
	this.sugarFrom = sugarFrom;
	this.sugarTo = sugarTo;
    }

    @Override
    public boolean check(Sweet sweet) {
	return sweet.getSugar()>= sugarFrom && sweet.getSugar() <= sugarTo; 
    }

}
