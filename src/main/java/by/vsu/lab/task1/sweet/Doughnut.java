package by.vsu.lab.task1.sweet;

public class Doughnut extends Sweet {

    int holes;
    
    public Doughnut(float weight, float sugar, String name, String manufacturer,int holes) {
	super(weight, sugar, name, manufacturer);
	this.holes = holes;
    }

    public String toString() {
	return String.format("%s holes %d", super.toString(), holes);
    }
    
}
