package by.vsu.lab.task4.service.logic;

import by.vsu.lab.task4.dao.UserDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.service.UserService;
import by.vsu.lab.task4.service.exception.ServiceException;

public class UserServiceImpl extends BaseService implements UserService {

    private UserDao userDao;
    
    @Override
    public User find(Long id) throws ServiceException {
	try {
	    return userDao.read(id);
	} catch (DaoException e) {
	   throw new ServiceException(e);
	}
    }

    @Override
    public User findBy(String name, String password)  throws ServiceException  {
	try {
	    return userDao.read(password, name);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    @Override
    public void update(User user) throws ServiceException  {
	try {
	    userDao.update(user);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}

    }
    
    

    @Override
    public void updatePassword(User user, String password) throws ServiceException {
	try {
	    userDao.updatePassword(user, password);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void create(User user)  throws ServiceException  {
	try {
	    userDao.create(user);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    @Override
    public void delete(User user)  throws ServiceException  {
	try {
	    userDao.delete(user.getId());
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isExists(String login) throws ServiceException {
	try {
	    return userDao.isExists(login);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    @Override
    public boolean isExists(Long id) throws ServiceException {
	try {
	    return userDao.isExists(id);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    
    
}
