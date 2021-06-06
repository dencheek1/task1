package by.vsu.lab.task4.dao;

import java.util.List;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.User;

public interface UserDao extends Dao<User> {

    public User read(String password, String name) throws DaoException ;
    public List<User> readAll() throws DaoException;
    public boolean isExists(String login) throws DaoException;
    public boolean isExists(Long id) throws DaoException;
    public void updatePassword(User user, String password) throws DaoException;
}
