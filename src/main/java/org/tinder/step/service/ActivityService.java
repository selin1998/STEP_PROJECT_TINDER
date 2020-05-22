package org.tinder.step.service;

import org.tinder.step.dao.ActivityDAO;
import org.tinder.step.entity.Activity;

public class ActivityService {
    ActivityDAO daoActivity=new ActivityDAO();

    public ActivityService() {
    }

    public boolean addLogout_time(Activity act){
        return daoActivity.add(act);
    }

    public Activity getActivityById(int id){
        return daoActivity.get(id);
    }

}
