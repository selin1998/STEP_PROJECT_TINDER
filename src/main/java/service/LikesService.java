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
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LikesService {


    DAO<Like> daoLike=new LikesDAO();
    UserService service;

    public LikesService() throws SQLException {

        service=new UserService();
    }

    public List<User> getAllLikedUsers(int user_id_from){
        return  getAllLikedUserIds(user_id_from).stream().map(i->service.getById(i)).collect(Collectors.toList());
    }

    public List<Integer> getAllLikedUserIds(int user_id_from){
        return daoLike.getAllBy(like -> like.getUser_id_from() == user_id_from).stream().map(l->l.getUser_id_to()).collect(Collectors.toList());

    }

    public void add(Like like){

        if(doesLikeExist(like.getUser_id_from(),like.getUser_id_to())) daoLike.add(like);
    }

    public boolean doesLikeExist(int user_id_from,int user_id_to){
        Predicate<Like>l1=l->l.getUser_id_from()==user_id_from;
        Predicate<Like>l2=l->l.getUser_id_to()==user_id_to;

        return daoLike.getAllBy(l1.and(l2)).isEmpty();
    }


    public void remove (int user_id_from,int user_id_to){
        if(!doesLikeExist(user_id_from,user_id_to)) daoLike.remove(new Like(user_id_from,user_id_to));

    }


}
