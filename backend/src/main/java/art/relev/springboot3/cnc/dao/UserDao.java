package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.model.User;

public interface UserDao extends CNCDao<User> {
    User getUserByName(String name);
}
