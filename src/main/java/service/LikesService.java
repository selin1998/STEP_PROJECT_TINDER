package service;

import dao.DAO;
import dao.LikesDAO;
import db.DatabaseConnection;
import entity.Like;
import entity.User;
import util.TemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class LikesService {
    private final TemplateEngine engine;
    DatabaseConnection db=new DatabaseConnection();
    Connection con=db.connect();
    DAO<Like> daoLike=new LikesDAO(con);
    UserService service;

    public LikesService(TemplateEngine engine) throws SQLException {
        this.engine = engine;
        service=new UserService(engine);
    }

    public List<User> getAllLikedUsersIds(int user_id_from){
        List<Integer> user_id_tos = daoLike.getAll().stream().filter(like -> like.getUser_id_from() == user_id_from).map(l -> l.getUser_id_to()).collect(Collectors.toList());
        return  user_id_tos.stream().map(i->service.getById(i)).collect(Collectors.toList());

    }
}
