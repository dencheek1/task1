package by.vsu.lab.task4.entitys;

public class User extends Entity {

    private UserType type;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
	return "User [type=" + type + ", login=" + login + ", getId()=" + getId() + "]";
    }
    
    
    
}
