package by.vsu.lab.task4.service;

import java.util.List;

import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.service.exception.ServiceException;

public interface ApplicantResultService {

    public void create(ExamResult result) throws ServiceException;
    public void update(ExamResult result)throws ServiceException;
    public void update(ExamResult oldResult, ExamResult newResult)throws ServiceException;
    public void delete(ExamResult result)throws ServiceException;
    public ExamResult find(Applicant apl, Examination exam) throws ServiceException;
    public List<ExamResult> findAll(Applicant apl) throws ServiceException;
    
}
