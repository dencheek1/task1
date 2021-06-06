package by.vsu.lab.task4.service.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.dao.ExamResultDao;
import by.vsu.lab.task4.dao.FacultyDao;
import by.vsu.lab.task4.dao.FacultyRequirementsDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.service.FacultyService;
import by.vsu.lab.task4.service.exception.ServiceException;

public class FacultyServiceImpl implements FacultyService {
    private FacultyDao facultyDao;
    private FacultyRequirementsDao facultyRquirementsDao;
    private ExamResultDao resultDao;
    private static final Logger logger = LogManager.getLogger();
    
    
    @Override
    public Faculty find(Long id) {
	try {
	    return facultyDao.read(id);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public List<FacultyRequirement> getRequirements(Faculty faculty) throws ServiceException {
	try {
	    return facultyRquirementsDao.readFacultyRequirements(faculty);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public List<Applicant> selectAplicants(List<Applicant> applicants, Faculty faculty) {
	Map<Applicant, Integer> resultList = new HashMap<Applicant, Integer>();
	// resultList.com
	List<Applicant> result = new ArrayList<Applicant>();
	LinkedList<FacultyRequirement> facultyRequirement = new LinkedList<FacultyRequirement>();
	List<FacultyRequirement> reqForTest;
	Integer sum;
	try {

	    top: for (Applicant applicant : applicants) {
		facultyRequirement.addAll(facultyRquirementsDao.readFacultyRequirements(faculty));
		sum = applicant.getSertificate();
		while (!facultyRequirement.isEmpty()) {
		    FacultyRequirement requirement = facultyRequirement.poll();
		    reqForTest = facultyRequirement.stream().filter(c -> c.getGroup() == requirement.getGroup())
			    .collect(Collectors.toList());
		    reqForTest.add(requirement);
		    ExamResult res = checkApplicantResults(resultDao.readByAplicant(applicant), reqForTest);
		    if (res != null) {
			sum += res.getResult();
		    } else
			continue top;
		}
		resultList.put(applicant, sum);
	    }
	    resultList.entrySet().stream().sorted((h1, h2) -> h2.getValue().compareTo(h1.getValue()))
		    .map(Map.Entry::getKey).collect(Collectors.toList()).stream();

	    resultList = resultList.entrySet().stream().sorted(Map.Entry.<Applicant, Integer>comparingByValue())
		    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
			    HashMap::new));
	    result.addAll(resultList.entrySet().stream().sorted((h1, h2) -> h2.getValue().compareTo(h1.getValue()))
		    .map(Map.Entry::getKey).collect(Collectors.toList()));

	    result = result.subList(0, faculty.getCount() - result.size() > 0 ? result.size() : faculty.getCount());
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return result;
    }

    @Override
    public void update(Faculty faculty) throws ServiceException {
	try {
	    if (faculty.getId() == 0)
		facultyDao.create(faculty);
	    else
		facultyDao.update(faculty);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}

    }

    @Override
    public void delete(Faculty faculty) throws ServiceException {
	try {
	    facultyDao.delete(faculty.getId());
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    protected ExamResult checkApplicantResults(List<ExamResult> applicantResults,
	    List<FacultyRequirement> facultyRequirement) {
	ArrayList<ExamResult> selectedResults = new ArrayList<ExamResult>();
	for (FacultyRequirement req : facultyRequirement) {
	    ExamResult selectedResult = applicantResults.stream()
		    .filter(r -> r.getExam().equals(req.getExam()) && r.getResult() >= req.getValue()).findAny()
		    .orElse(null);
	    if (selectedResult != null && selectedResult.getChecked())
		selectedResults.add(selectedResult);
	    // if() facultyRequirement.add(req);
	}
	logger.debug(selectedResults);
	try {
	    Collections.sort(selectedResults, (res1, res2) -> res2.getResult() - res1.getResult());
	    logger.debug(selectedResults.get(0));
	    return selectedResults.get(0);
	} catch (IndexOutOfBoundsException | NullPointerException e) {
	    return null;
	}
    }

    public int countAplicantResult(Applicant applicant) {
	int sum = 0;
	try {
	    for (ExamResult result : resultDao.readByAplicant(applicant)) {
		sum += result.getResult();
	    }
	} catch (DaoException e) {
	    e.printStackTrace();
	}
	return sum + applicant.getSertificate();
    }

    public void setFacultyDao(FacultyDao facultyDao) {

	    this.facultyDao = facultyDao;

    }

    public void setFacultyRquirementsDao(FacultyRequirementsDao facultyRquirementsDao) {
	
	    this.facultyRquirementsDao = facultyRquirementsDao;
    }

    public void setResultDao(ExamResultDao resultDao) {

		this.resultDao = resultDao;
	
    }

    @Override
    public List<Faculty> findAll() throws ServiceException {
	try {
	    return facultyDao.readAll();
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

}
