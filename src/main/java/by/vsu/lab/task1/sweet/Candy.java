package by.vsu.lab.task1.sweet;

import by.vsu.lab.task1.sweet.CandyTypes.CandyTypes;

public class Candy extends Sweet {

   
    private CandyTypes type;

    public Candy(float weight, float sugar, String name, String manufacturer, CandyTypes type) {
	super(weight, sugar, name, manufacturer);
	this.type = type;
    }

    public String toString() {
	return String.format("%s  %s", super.toString(), type);
    }
    
}
