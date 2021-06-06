package by.vsu.lab.task4.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.lab.task4.dao.FacultyDao;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;

public class FacultyDaoImpl extends BaseDao implements FacultyDao {

    @Override
    public void update(Faculty entity) throws DaoException {
	String sql = "UPDATE faculty SET name = ?, count = ? WHERE id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setString(1, entity.getName());
	    statement.setInt(2, entity.getCount());
	    statement.setLong(3, entity.getId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {
		statement.close();
	    } catch (Exception e) {
	    }
	}

    }

    @Override
    public void delete(Long id) throws DaoException {
	String sql = "DELETE FROM faculty WHERE id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {
		statement.close();
	    } catch (Exception e) {
	    }
	}

    }

    @Override
    public Faculty read(Long id) throws DaoException {
	String sql = "SELECT name, count FROM faculty WHERE id = ?;";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    resultSet = statement.executeQuery();
	    Faculty faculty = null;

	    while (resultSet.next()) {
		faculty = new Faculty();
		faculty.setId(id);
		faculty.setName(resultSet.getString("name"));
		faculty.setCount(resultSet.getInt("count"));
	    }
	    return faculty;
	} catch (SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {
		resultSet.close();
	    } catch (Exception e) {
	    }
	    try {
		statement.close();
	    } catch (Exception e) {
	    }
	}
    }

    @Override
    public Long create(Faculty entity) throws DaoException {
	String sql = "INSERT INTO faculty (name, count) VALUES(?, ?);";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    statement.setString(1, entity.getName());
	    statement.setInt(2, entity.getCount());
	    statement.executeUpdate();
	    Long id = null;
	    resultSet = statement.getGeneratedKeys();
	    while (resultSet.next()) {
		id = resultSet.getLong(1);
	    }
	    return id;
	} catch (SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {
		resultSet.close();
	    } catch (Exception e) {
	    }
	    try {
		statement.close();
	    } catch (Exception e) {
	    }
	}
    }

    @Override
    public List<Faculty> readAll() throws DaoException {
	List<Faculty> facultyList = new ArrayList<Faculty>();
	String sql = "SELECT id, name, count FROM faculty;";
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	try {
	    statement = getConnection().prepareStatement(sql);
	    resultSet = statement.executeQuery();
	    while (resultSet.next()) {
		Faculty faculty = new Faculty();
		faculty.setId(resultSet.getLong("id"));
		faculty.setName(resultSet.getString("name"));
		faculty.setCount(resultSet.getInt("count"));
		facultyList.add(faculty);
	    }
	    return facultyList;
	} catch (SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {
		resultSet.close();
	    } catch (Exception e) {
	    }
	    try {
		statement.close();
	    } catch (Exception e) {
	    }
	}
    }

}
