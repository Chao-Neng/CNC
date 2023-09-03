package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.RoleDao;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.Role;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.RoleParam;
import art.relev.springboot3.cnc.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Override
    public Role create(RoleParam.Create param) {
        Resource resource = Resource.builder().resourceName("role").build();
        // TODO: temp resource ownerId
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user && user.getResource() != null) {
                resource.getOwnerIdSet().add(user.getResource().getId());
            }
        } catch (Exception ignore) {
        }
        Role role = Role.builder().resource(resource).name(param.getName()).build();
        roleDao.save(role);
        return role;
    }

    @Override
    public void delete(RoleParam.Delete param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Role role = Role.builder().resource(resource).build();
        roleDao.delete(role);
    }

    @Override
    public Role update(RoleParam.Update param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Role role = Role.builder().resource(resource).name(param.getName()).build();
        role = roleDao.save(role);
        return role;
    }

    @Override
    public Role query(RoleParam.Query param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Role role = roleDao.getRoleByResource(resource);
        return role;
    }
}
