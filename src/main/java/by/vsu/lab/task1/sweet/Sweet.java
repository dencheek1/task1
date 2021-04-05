package by.vsu.lab.task1.sweet;

import java.io.Serializable;

public abstract class Sweet implements Serializable {

    protected float weight;		// Weight in grams
    
    protected float sugar;		// Weight of sugar in grams
    
    protected String name;
    
    protected String manufacturer;

    public Sweet(float weight, float sugar, String name, String manufacturer) {
	if(weight < 0) throw new IllegalArgumentException("Weight can't be negative ");
	if(sugar < 0) throw new IllegalArgumentException("Sugar weight can't be negative ");
	if(sugar > weight) throw new IllegalArgumentException("Sugar weight can't be more then weight");
	this.weight = weight;
	this.sugar = sugar;
	this.name = name;
	this.manufacturer = manufacturer;
    }

    public float getWeight() {
	return weight;
    }

    public float getSugar() {
	return sugar;
    }

    public String getName() {
	return name;
    }

    public String getManufacturer() {
	return manufacturer;
    }

    @Override 
    public String toString(){
	return  String.format("Weight %fg sugar %fg %s %s" , weight, sugar, name, manufacturer);
    }

}
