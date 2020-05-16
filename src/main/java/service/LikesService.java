package service;

import dao.DAO;
import dao.LikesDAO;
import dao.UserDAO;
import db.DatabaseConnection;
import entity.Like;
import entity.User;
import util.TemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class LikesService {

    UserDAO userDAO=new UserDAO();
    DAO<Like> daoLike=new LikesDAO();
    UserService service;

    public LikesService() throws SQLException {

        service=new UserService(userDAO);
    }

    public List<User> getAllLikedUsers(int user_id_from){
        List<Integer> user_id_tos = daoLike.getAllBy(like -> like.getUser_id_from() == user_id_from).stream().map(l->l.getUser_id_to()).collect(Collectors.toList());
        return  user_id_tos.stream().map(i->service.getById(i)).collect(Collectors.toList());

    }

    public void add(Like like){
        daoLike.add(like);
    }


}
