package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.model.Authority;

public interface AuthorityDao extends CNCDao<Authority> {
    Authority getAuthorityByName(String name);
}
