package by.vsu.lab.task4.dao.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.lab.task4.dao.ApplicantDao;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;

public class ApplicantDaoImpl extends BaseDao implements ApplicantDao {

    
    
    @Override
    public List<Applicant> readAll() throws DaoException {
	List<Applicant> aplicantList = new ArrayList<Applicant>();
	String sql = "SELECT user_id, name, surname, certificate, faculty_id FROM aplicant;";
	PreparedStatement statement = null ;
	ResultSet resultSet = null;
	
	try {
	    statement = getConnection().prepareStatement(sql);
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		Applicant applicant = new Applicant();
		applicant.setId(resultSet.getLong("user_id"));
		applicant.setName(resultSet.getString("name"));
		applicant.setSurname(resultSet.getString("surname"));
		applicant.setSertificate(resultSet.getInt("certificate"));
		applicant.setFacultyId(resultSet.getLong("faculty_id"));
		aplicantList.add(applicant);
	    }
	    return aplicantList;
	} catch(SQLException e) {
	   throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public void update(Applicant entity) throws DaoException {
	String sql = "UPDATE aplicant SET name=?, surname=?, certificate=?, faculty_id=? WHERE user_id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setString(1, entity.getName());
	    statement.setString(2, entity.getSurname());
	    statement.setInt(3, entity.getSertificate());
	    statement.setLong(4, entity.getFacultyId());
	    statement.setLong(5,entity.getId());
	    statement.executeUpdate();
	}catch(SQLException e) {
	    throw new DaoException(e);
	}finally {
	    try {statement.close();}catch(Exception e) {}
	}
    }

    @Override
    public void delete(Long id) throws DaoException {
	String sql = "DELETE FROM aplicant WHERE user_id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    statement.executeUpdate();
	}catch(SQLException e) {
	    throw new DaoException(e);
	}finally {
	    try {statement.close();}catch(Exception e) {}
	}
    }

    @Override
    public Applicant read(Long id) throws DaoException {
	String sql = "SELECT user_id, name, surname, certificate, faculty_id FROM aplicant WHERE user_id = ?;";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    resultSet = statement.executeQuery();
	    Applicant applicant = null;
	    while(resultSet.next()) {
		applicant = new Applicant();
		applicant.setId(id);
		applicant.setName(resultSet.getString("name"));
		applicant.setSurname(resultSet.getString("surname"));
		applicant.setSertificate(resultSet.getInt("certificate"));
		applicant.setFacultyId(resultSet.getLong("faculty_id"));
	    }
	    return applicant;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public Long create(Applicant entity) throws DaoException {
	String sql = "INSERT INTO aplicant (user_id, name, surname, certificate, faculty_id) VALUES(?, ?, ?, ?, ?);";
	PreparedStatement statement = null;
	
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, entity.getId());
	    statement.setString(2, entity.getName());
	    statement.setString(3, entity.getSurname());
	    statement.setInt(4, entity.getSertificate());
	    statement.setLong(5, entity.getFacultyId());
	    statement.executeUpdate();

            return entity.getId();
	}catch(SQLException e){
	    throw new DaoException(e);
	}finally {
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public List<Applicant> readByFacultetId(long id) throws DaoException {
	List<Applicant> aplicantList = new ArrayList<Applicant>();
	String sql = "SELECT user_id, name, surname, certificate, faculty_id FROM aplicant WHERE faculty_id = ?;";
	PreparedStatement statement = null ;
	ResultSet resultSet = null;
	
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, id);
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		Applicant applicant = new Applicant();
		applicant.setId(resultSet.getLong("user_id"));
		applicant.setName(resultSet.getString("name"));
		applicant.setSurname(resultSet.getString("surname"));
		applicant.setSertificate(resultSet.getInt("certificate"));
		applicant.setFacultyId(resultSet.getLong("faculty_id"));
		aplicantList.add(applicant);
	    }
	    return aplicantList;
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

}
