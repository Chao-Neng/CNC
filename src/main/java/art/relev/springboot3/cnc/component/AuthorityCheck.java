package art.relev.springboot3.cnc.component;

import art.relev.springboot3.cnc.dao.AuthorityDao;
import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.exclude.CNCResource;
import art.relev.springboot3.cnc.model.Authority;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.Role;
import art.relev.springboot3.cnc.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorityCheck {
    private final ResourceDao resourceDao;
    private final AuthorityDao authorityDao;
    private final ApplicationContext applicationContext;

    public boolean check(Object paramObject, Object defaultAllowOwnerObject, Object defaultBlackListModeObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof User user && paramObject instanceof CNCParam param && defaultAllowOwnerObject instanceof Boolean defaultAllowOwner && defaultBlackListModeObject instanceof Boolean defaultBlackListMode)) {
            return false;
        }
        String authorityName = param.getAuthorityName();
        String resourceName = param.getResourceName();
        Authority authority = authorityDao.getAuthorityByName(authorityName);
        if (authority == null) {
            authority = Authority.builder().name(authorityName).resourceName(resourceName).allowOwner(defaultAllowOwner).blackListMode(defaultBlackListMode).build();
            authorityDao.save(authority);
        }
        for (Role item : authority.getRoleSet()) {
            for (Role role : user.getRoleSet()) {
                if (role.getName().equals(item.getName())) {
                    return !authority.getBlackListMode();
                }
            }
        }
        if (authority.getBlackListMode()) {
            return true;
        }
        if (authority.getAllowOwner()) {
            try {
                Long resourceId = param.getResourceId();
                if (resourceId == null) {
                    resourceId = param.getParentResourceId();
                }
                if (resourceId != null) {
                    Boolean result = checkResource(resourceId, user.getResource().getId());
                    return (result != null) && result;
                }
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    private Boolean checkResource(Long resourceId, Long userResourceId) {
        Resource resource = resourceDao.getResourceById(resourceId);
        Set<Long> ownerResourceIdSet = new HashSet<>();
        while (resource != null) {
            if (applicationContext.getBean(resource.getResourceName() + "Dao") instanceof CNCDao<?> dao) {
                if (dao.getReferenceById(resource.getId()) instanceof CNCResource cncResource) {
                    if (cncResource.getResource().getOwnerIdSet() != null) {
                        ownerResourceIdSet.addAll(cncResource.getResource().getOwnerIdSet());
                    }
                    resource = cncResource.getResource().getParentResource();
                }
            }
        }
        for (Long ownerResourceId : ownerResourceIdSet) {
            if (Objects.equals(ownerResourceId, userResourceId)) {
                return true;
            }
        }
        return null;
    }
}
