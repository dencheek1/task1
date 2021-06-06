package by.vsu.lab.task4.service;

import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.service.exception.ServiceException;

public interface UserService {

    public User find(Long id) throws ServiceException;
    public User findBy(String name, String password) throws ServiceException;
    public void update (User user) throws ServiceException;
    public void create(User user) throws ServiceException;
    public void delete(User user) throws ServiceException;
    public boolean isExists(String login) throws ServiceException;
    public boolean isExists(Long id) throws ServiceException;
    public void updatePassword(User user, String password) throws ServiceException;
    
}
