package by.vsu.lab.task4.entitys;

public class Applicant extends Entity {

    private String name;
    private String surname;
    private int sertificate;
    private long facultyId;
    
    
    public Applicant() {}
    
    public Applicant(Long id) {
	setId(id);
    }
    
    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public int getSertificate() {
        return sertificate;
    }

    public void setSertificate(int sertificate) {
        this.sertificate = sertificate;
    }

    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("id: ").append(getId()).append("\n");
	builder.append("name: ").append(name).append("\n");
	builder.append("surname: ").append(surname).append("\n");
	builder.append("sertificate: ").append(sertificate).append("\n");
	builder.append("facultiId: ").append(facultyId).append("\n");
	return builder.toString();
    }
    
}
