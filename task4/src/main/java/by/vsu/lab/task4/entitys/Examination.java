package by.vsu.lab.task4.entitys;

public enum Examination {
    RU_LENG("lenguage",1), BY_LENG("lenguage", 2), MATH("math",3), PHITH("physics",4);
    
    private final String name;
    private final long id;
    
    

    private Examination(String name, long id) {
	this.name = name;
	this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public long getId() {
	return id;
    }
    
}
