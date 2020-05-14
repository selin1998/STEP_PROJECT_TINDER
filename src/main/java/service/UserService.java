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
        User resp = userDao.getByLoginAndPassword(user.getLogin(),user.getPassword());
        return resp != null && resp.getUser_id()==(user.getUser_id());
    }


}
