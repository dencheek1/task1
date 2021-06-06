package by.vsu.lab.task4.dao.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.lab.task4.dao.FacultyRequirementsDao;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;

public class FacultyRequirementsDaoImpl extends BaseDao implements FacultyRequirementsDao {

    @Override
    public void create(FacultyRequirement requirements) throws DaoException {
	String sql = "INSERT INTO speciality_requirements "
		+ "(spec_id, exam_id, min, `group`) VALUES(?, ?, ?, ?);";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, requirements.getFaculty().getId());
	    statement.setLong(2, requirements.getExam().getId());
	    statement.setInt(3, requirements.getValue());
	    statement.setInt(4, requirements.getGroup());
	    statement.executeUpdate();
	    
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {statement.close();} catch(Exception e) {}
	}
	
    }

    @Override
    public void update(FacultyRequirement requirements) throws DaoException {
	String sql = "UPDATE speciality_requirements SET spec_id=?, exam_id=?, min=?, `group`=?"
		+ " WHERE spec_id=? and exam_id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, requirements.getFaculty().getId());
	    statement.setLong(2, requirements.getExam().getId());
	    statement.setInt(3, requirements.getValue());
	    statement.setInt(4, requirements.getGroup());
	    statement.setLong(5, requirements.getFaculty().getId());
	    statement.setLong(6, requirements.getExam().getId());
	    statement.executeUpdate();
	    
	} catch(SQLException e) {
	    throw new DaoException(e);
	} finally {
	    try {statement.close();} catch(Exception e) {}
	}
    }

    @Override
    public FacultyRequirement read(Faculty faculty, Examination exam) throws DaoException {
	String sql = "SELECT min, `group` FROM speciality_requirements"
		+ " WHERE spec_id=? and exam_id =?;";
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    FacultyRequirement requirement = null;
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, faculty.getId());
	    statement.setLong(2, exam.getId());
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		requirement = new FacultyRequirement();
		requirement.setFaculty(faculty);
		requirement.setExam(exam);
		requirement.setValue(resultSet.getInt("min"));
		requirement.setGroup(resultSet.getInt("group"));
		
	    }
	    return requirement;
	} catch(SQLException e) {
	    throw new DaoException(e);
	}finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public List<FacultyRequirement> readFacultyRequirements(Faculty faculty) throws DaoException {
	String sql = "SELECT name, min, `group` FROM speciality_requirements"
		+ " JOIN exams ON speciality_requirements.exam_id  = exams.id "
		+ " WHERE spec_id = ?;";
	List<FacultyRequirement> reqList = new ArrayList<FacultyRequirement>();
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, faculty.getId());
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		FacultyRequirement req = new FacultyRequirement();
		req.setFaculty(faculty);
		req.setExam(Examination.valueOf(resultSet.getString("name")));
		req.setValue(resultSet.getInt("min"));
		req.setGroup(resultSet.getInt("group"));
		
		reqList.add(req);
	    }
	    return reqList;
	} catch(SQLException e) {
	    throw new DaoException(e);
	}finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public void delete(FacultyRequirement requirements) throws DaoException {
	String sql = "DELETE FROM speciality_requirements WHERE spec_id=? AND exam_id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, requirements.getFaculty().getId());
	    statement.setLong(2, requirements.getExam().getId());
	    statement.executeUpdate();
	} catch(SQLException e) {
	    throw new DaoException(e);
	}finally {
	    try{ statement.close(); } catch(Exception e) {}
	}
	
    }

}
