package by.vsu.lab.task4.test;

import java.sql.Connection;
import java.util.List;

import by.vsu.lab.task4.dao.UserDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.dao.daoimpl.UserDaoImpl;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;

public class UserDaoTests extends BaseTest {

    UserDaoImpl userDao;
    User baseUser;
    
    public UserDaoTests(Connection connection) {
	super(connection);
	userDao = new UserDaoImpl();
	userDao.setConnection(getConnection());
	baseUser = new User();
	baseUser.setId(1L);
    }
    
    
    
    @Override
    public void readTest() {
	System.out.println("Read test");
	try {
	    User user = userDao.read(baseUser.getId());
	    System.out.println("Read by id");
	    System.out.println(user);
	    System.out.println("Read user by login");
	    user = userDao.read("1111",user.getLogin());
	    System.out.println(user);
	    for(User u: userDao.readAll()) {
		System.out.println(u);
	    }
	    System.out.println("Exists test\n\n");
	    System.out.println(userDao.isExists(1L));
	    System.out.println(userDao.isExists(100L));
	    System.out.println(userDao.isExists(baseUser.getLogin()));
	    System.out.println(userDao.isExists("Valya"));
	    
	} catch(DaoException e) {
	    System.out.println("Read test Fail " + e);
	}
    }

    @Override
    public void createTest() {
	System.out.println("Create test");
	User user = new User();
	user.setLogin("Den");
	user.setType(UserType.USER);
	try {
	    System.out.println(user);
	    user.setId(userDao.create(user));
	    System.out.println(user.getId());
	    baseUser = user;
	} catch(DaoException e) {
	    System.out.println("Create test Fail " + e);
	}
    }

    @Override
    public void updateTest() {
	System.out.println("Update test");
	try {
	    User user = new User();
	    user.setId(baseUser.getId());
	    user.setLogin("Roma");
	    user.setType(UserType.ADMIN);
	    System.out.println("before: " + userDao.read(user.getId()));
	    userDao.update(user);
	    System.out.println("after: " + userDao.read(user.getId()));
	} catch(DaoException e) {
	    System.out.println("Update test Fail "+ e);
	}
    }

    @Override
    public void deleteTest() {
	System.out.println("Delete test");
	try {
	    userDao.delete(baseUser.getId());
	    System.out.println("Deleted");
	} catch (DaoException e) {
	    System.out.println("Delete test fail");
	}
    }

    
    
}
