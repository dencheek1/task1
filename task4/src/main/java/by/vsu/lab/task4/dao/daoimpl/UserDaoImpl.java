package by.vsu.lab.task4.dao.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.lab.task4.dao.UserDao;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public void update(User entity) throws DaoException {
	String sql = "UPDATE users SET login=?,`type`=? WHERE id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setString(1, entity.getLogin());
	    statement.setString(2, entity.getType().toString());
	    statement.setLong(3, entity.getId());
	    statement.executeUpdate();
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ statement.close(); } catch(Exception e) {}
	}
    }
    
    public void updatePassword(User user, String password) throws DaoException {
	String sql = "UPDATE users SET password=? WHERE id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setString(1, password);
	    statement.setLong(2, user.getId());
	    statement.executeUpdate();
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public void delete(Long id) throws DaoException {
	
	String sql = "DELETE FROM users WHERE id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    statement.executeUpdate();
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public User read(Long id) throws DaoException {
	String sql = "SELECT login, `type` FROM users WHERE id=?";
	User user = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    resultSet = statement.executeQuery();
	    user = new User();
	    while(resultSet.next()) {
		user.setLogin(resultSet.getString("login"));
		user.setType(UserType.valueOf(resultSet.getString("type")));
		user.setId(id);
	    }
	    return user;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public Long create(User entity) throws DaoException {
	String sql = "INSERT INTO users (login, password, `type`) VALUES(?, ?, ?);";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	Long id = null;
	try {
	    statement = getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, entity.getLogin());
	    statement.setString(2, "1111");
	    statement.setString(3, entity.getType().toString());
	    statement.executeUpdate();
	    resultSet = statement.getGeneratedKeys();
	    if(resultSet.next()) {
        	id = resultSet.getLong(1);
            }
	    return id;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public User read(String password, String name) throws DaoException {
	String sql = "SELECT id, `type` FROM users WHERE login=? and password=?;";
	User user = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setString(1, name);
	    statement.setString(2, password);
	    resultSet = statement.executeQuery();
	    user = new User();
	    while(resultSet.next()) {
		user.setLogin(name);
		user.setType(UserType.valueOf(resultSet.getString("type")));
		user.setId(resultSet.getLong("id"));
	    }
	    return user;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }
    @Override
    public List<User> readAll() throws DaoException{
	String sql = "SELECT id, `type`, login FROM users;";
	List<User> users = new ArrayList<User>();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		User user = new User();
		user.setId(resultSet.getLong("id"));
		user.setType(UserType.valueOf(resultSet.getString("type")));
		user.setLogin(resultSet.getString("login"));
		users.add(user);
	    }
	    return users;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public boolean isExists(String login) throws DaoException {
	String sql = "SELECT EXISTS(SELECT id FROM users WHERE login=?);";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean isExists = false;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setString(1, login);
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		isExists = resultSet.getInt(1)!=0;
	    }
	    return isExists;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public boolean isExists(Long id) throws DaoException {
	String sql = "SELECT EXISTS(SELECT id FROM users WHERE id=?);";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	boolean isExists = false;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		isExists = resultSet.getInt(1)!=0;
	    }
	    return isExists;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }
    
    

}
