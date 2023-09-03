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

    public boolean check(Object paramObject, Object resourceNameObject, Object nameObject, Object defaultAllowOwnerObject, Object defaultBlackListModeObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof User user && paramObject instanceof CNCParam param && resourceNameObject instanceof String resourceName && nameObject instanceof String name && defaultAllowOwnerObject instanceof Boolean defaultAllowOwner && defaultBlackListModeObject instanceof Boolean defaultBlackListMode)) {
            return false;
        }
        Authority authority = authorityDao.getAuthorityByName(name);
        if (authority == null) {
            authority = Authority.builder().name(name).resourceName(resourceName).allowOwner(defaultAllowOwner).blackListMode(defaultBlackListMode).build();
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
                Boolean result = null;
                if (param.getResourceId() != null) {
                    result = checkResource(resourceName, param.getResourceId(), user.getResource().getId());
                } else if (param.getParentResourceId() != null) {
                    String allClassName = param.getClass().getName().split("Param\\$")[0];
                    int dotIndex = allClassName.lastIndexOf(".");
                    allClassName = allClassName.substring(0, dotIndex - "param".length()) + "model" + allClassName.substring(dotIndex);
                    String className = Class.forName(allClassName).getMethod("getParentResource").getReturnType().getSimpleName();
                    result = checkResource(className, param.getParentResourceId(), user.getResource().getId());
                }
                if (result != null) {
                    return result;
                }
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    private Boolean checkResource(String resourceName, Long resourceId, Long userResourceId) {
        Resource resource = resourceDao.getResourceById(resourceId);
        Set<Long> ownerIdSet = new HashSet<>();
        while (resource != null) {
            if (applicationContext.getBean(resource.getTypeName() + "Dao") instanceof CNCDao<?> dao) {
                if (dao.getReferenceById(resource.getId()) instanceof CNCResource cncResource) {
                    if (cncResource.getResource().getOwnerIdSet() != null) {
                        ownerIdSet.addAll(cncResource.getResource().getOwnerIdSet());
                    }
                    resource = cncResource.getResource().getParentResource();
                }
            }
        }
        for (Long ownerResourceId : ownerIdSet) {
            if (Objects.equals(ownerResourceId, userResourceId)) {
                return true;
            }
        }
        return null;
    }
}
