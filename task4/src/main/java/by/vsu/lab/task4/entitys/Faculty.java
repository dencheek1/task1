package by.vsu.lab.task4.entitys;

public class Faculty extends Entity {

    
    private int count;
    private String name;
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
	return "Faculty [count=" + count + ", name=" + name + "]";
    }
    
}
