package by.vsu.lab.task4.entitys;

public class FacultyRequirement {

    private Examination exam;
    private Integer group;
    private Faculty faculty;
    private Integer value;
    
    
    
    public void setGroup(Integer group) {
        this.group = group;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public Examination getExam() {
        return exam;
    }
    public void setExam(Examination exam) {
        this.exam = exam;
    }
    public Integer getGroup() {
        return group;
    }
    public void setGroup(int group) {
        this.group = group;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
	return "FacultyRequirement [exam=" + exam + ", group=" + group + ", faculty=" + faculty.getId() + ", value=" + value
		+ "]";
    }
    
    
    
    
}
