package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.Role;

public interface RoleDao extends CNCDao<Role> {
    Role getRoleByResource(Resource resource);
}
