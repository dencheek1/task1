package by.vsu.lab.task1.present;

import java.util.ArrayList;

import by.vsu.lab.task1.sweet.Candy;
import by.vsu.lab.task1.sweet.Cookies;
import by.vsu.lab.task1.sweet.Doughnut;
import by.vsu.lab.task1.sweet.Sweet;
import by.vsu.lab.task1.sweet.CandyTypes.CandyTypes;

public class SimplePresentBuilder implements PresentBuilder{
    
    @Override
    public Present build() {
	
	ArrayList<Sweet> sweets = new ArrayList<Sweet>();
	
	sweets.add(new Candy(25, 15, "Mars", "Mars", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(25, 15, "Mars", "Mars", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(25, 15, "Mars", "Mars", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(25, 15, "Mars", "Mars", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(15, 10, "Sugar Daddy", "Nesty", CandyTypes.CHARAMEL));
	sweets.add(new Candy(15, 10, "Sugar Daddy", "Nesty", CandyTypes.CHARAMEL));
	sweets.add(new Candy(15, 10, "Sugar Daddy", "Nesty", CandyTypes.CHARAMEL));
	sweets.add(new Candy(15, 10, "Sugar Daddy", "Nesty", CandyTypes.CHARAMEL));
	sweets.add(new Candy(15, 10, "Sugar Daddy", "Nesty", CandyTypes.CHARAMEL));
	sweets.add(new Candy(20, 16, "Taffy", "Kommunarka", CandyTypes.SOURS));
	sweets.add(new Candy(20, 16, "Taffy", "Kommunarka", CandyTypes.SOURS));
	sweets.add(new Candy(20, 16, "Taffy", "Kommunarka", CandyTypes.SOURS));
	sweets.add(new Candy(20, 16, "Taffy", "Kommunarka", CandyTypes.SOURS));
	sweets.add(new Candy(20, 16, "Taffy", "Kommunarka", CandyTypes.SOURS));
	sweets.add(new Candy(20, 16, "Red october", "Konti", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(20, 16, "Red october", "Konti", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(20, 16, "Red october", "Konti", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(20, 16, "Red october", "Konti", CandyTypes.CHOCOLATE));
	sweets.add(new Candy(20, 16, "Red october", "Konti", CandyTypes.CHOCOLATE));
	sweets.add(new Cookies(40, 15, "Super kontik", "Konti"));
	sweets.add(new Cookies(40, 15, "Super kontik", "Konti"));
	sweets.add(new Cookies(40, 15, "Super kontik", "Konti"));
	sweets.add(new Cookies(40, 15, "Super kontik", "Konti"));
	sweets.add(new Cookies(40, 15, "Super kontik", "Konti"));
	sweets.add(new Cookies(40, 15, "Super kontik", "Konti"));
	sweets.add(new Doughnut(120, 30, "Today", "Elvan", 1));
	sweets.add(new Doughnut(120, 30, "Strange", "Elvan", 2));
	
	
	return new Present(sweets);
    }

}
