package service;

import dao.UserDAO;
import entity.User;

public class UserService {
    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }


    public int getUserId(String login,String password) {
        return userDao.getUserId(login,password);
    }

    public User getByLoginAndPassword(String login, String password) {
        return userDao.getByLoginAndPassword(login,password);
    }

    public boolean checkUser(User user){
        User result = userDao.getByLoginAndPassword(user.getLogin(),user.getPassword());
        return result != null && result.getPassword().equals(user.getPassword());  //&& result.getLogin().equals(user.getLogin());
    }



}
