package org.tinder.step.service;

import org.tinder.step.dao.LikesDAO;
import org.tinder.step.entity.Like;
import org.tinder.step.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LikesService {


    LikesDAO daoLike;
    UserService service;

    public LikesService() {
        daoLike = new LikesDAO();
        service = new UserService();
    }

    public List<Optional<User>> getAllLikedUsers(int user_id_from) {
        return getAllLikedUserIds(user_id_from).stream().map(i -> service.getById(i)).collect(Collectors.toList());

    }

    public List<Integer> getAllLikedUserIds(int user_id_from) {
        return daoLike.allLikesOfUser(user_id_from).stream().map(l -> l.getUser_id_to()).collect(Collectors.toList());

    }

    public void add(Like like) {

        if (!doesLikeExist(like.getUser_id_from(), like.getUser_id_to())) daoLike.add(like);
    }

    public boolean doesLikeExist(int user_id_from, int user_id_to) {
        return getAllLikedUserIds(user_id_from).contains(user_id_to);
    }


    public void remove(int user_id_from, int user_id_to) {
        if (doesLikeExist(user_id_from, user_id_to)) daoLike.remove(new Like(user_id_from, user_id_to));

    }


}
