package by.vsu.lab.task1.filters;

import by.vsu.lab.task1.sweet.Sweet;

public class NameFilter implements Filter {

    String name;

    public NameFilter(String name) {
	this.name = name;
    }

    @Override
    public boolean check(Sweet sweet) {

	return sweet.getName().equals(name);
    }

}
