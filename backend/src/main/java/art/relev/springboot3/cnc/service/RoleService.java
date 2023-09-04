package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.model.Role;
import art.relev.springboot3.cnc.param.RoleParam;

public interface RoleService {
    Role create(RoleParam.Create param);

    void delete(RoleParam.Delete param);

    Role update(RoleParam.Update param);

    Role query(RoleParam.Query param);
}
