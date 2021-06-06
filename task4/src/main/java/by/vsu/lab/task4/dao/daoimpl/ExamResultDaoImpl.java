package by.vsu.lab.task4.dao.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.lab.task4.dao.ExamResultDao;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;

public class ExamResultDaoImpl extends BaseDao implements ExamResultDao {

    @Override
    public void delete(ExamResult result) throws DaoException {
	String sql = "DELETE FROM aplicant_result WHERE aplicant_id=? and exa_id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, result.getAplicant().getId());
	    statement.setLong(2, result.getExam().getId());
	    statement.executeUpdate();
	    
	} catch(SQLException e) {
	   throw new DaoException(e);
	} finally {
	    try {statement.close();} catch(Exception e) {}
	}
	
    }
    
    @Override
    public void update(ExamResult result) throws DaoException {
	String sql = "UPDATE aplicant_result SET "
		+ " `result`=? , checked=? WHERE aplicant_id=? and exa_id=?;";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setInt(1, result.getResult());
	    statement.setBoolean(2, result.getChecked());
	    statement.setLong(3, result.getAplicant().getId());
	    statement.setLong(4, result.getExam().getId());
	    statement.executeUpdate();
	    
	} catch(SQLException e) {
		   throw new DaoException(e);
	} finally {
	    try {statement.close();} catch(Exception e) {}
	}
	
	
    }

    @Override
    public void create(ExamResult result) throws DaoException {
	String sql = "INSERT INTO aplicant_result "
		+ "(aplicant_id, exa_id, `result`, checked) VALUES(?, ?, ?, ?);";
	PreparedStatement statement = null;
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1,result.getAplicant().getId());
	    statement.setLong(2, result.getExam().getId());
	    statement.setInt(3, result.getResult());
	    statement.setBoolean(4, result.getChecked());
	    statement.executeUpdate();
	    
	} catch(SQLException e) {
		   throw new DaoException(e);
	} finally {
	    try {statement.close();} catch(Exception e) {}
	}
	
    }


    @Override
    public List<ExamResult> readByAplicant(Applicant applicant) throws DaoException {
	List<ExamResult> results = new ArrayList<ExamResult>();
	String sql = "SELECT result, name, checked  FROM aplicant_result " + 
		"JOIN exams ON aplicant_result.exa_id  = exams.id " + 
		"WHERE aplicant_id = ?;";
	PreparedStatement statement = null ;
	ResultSet resultSet = null;
	
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, applicant.getId());
	    resultSet = statement.executeQuery();
	    while(resultSet.next()) {
		ExamResult result = new ExamResult();
		result.setAplicant(applicant);
		result.setResult(resultSet.getInt("result"));
		result.setChecked(resultSet.getBoolean("checked"));
		result.setExam(Examination.valueOf(resultSet.getString("name")));
		results.add(result);
	    }
	    return results;
	} catch(SQLException e) {
		   throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }

    @Override
    public ExamResult read(Applicant applicant, Examination examination) throws DaoException{
	String sql = "SELECT `result`, checked FROM aplicant_result " + 
		"JOIN exams ON aplicant_result.exa_id  = exams.id " + 
		"WHERE aplicant_id = ? AND id = ?;";
	PreparedStatement statement = null ;
	ResultSet resultSet = null;
	
	try {
	    statement = getConnection().prepareStatement(sql);
	    statement.setLong(1, applicant.getId());
	    statement.setLong(2, examination.getId());
	    resultSet = statement.executeQuery();
	    ExamResult result = null;
	    while(resultSet.next()) {
		result = new ExamResult();
		result.setAplicant(applicant);
		result.setExam(examination);
		result.setResult(resultSet.getInt("result"));
		result.setChecked(resultSet.getBoolean("checked"));
	    }
	    return result;
	} catch(SQLException e) {
		   throw new DaoException(e);
	} finally {
	    try{ resultSet.close(); } catch(Exception e) {}
	    try{ statement.close(); } catch(Exception e) {}
	}
    }
    
}
