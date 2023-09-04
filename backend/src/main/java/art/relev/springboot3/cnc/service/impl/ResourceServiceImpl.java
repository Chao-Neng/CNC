package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.exclude.CNCCreateParam;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.ResourceParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceDao resourceDao;

    @Override
    @CachePut(value = Resource.RESOURCE_NAME, key = "#result.id")
    public Resource query(ResourceParam.Query param) {
        Resource resource = resourceDao.getResourceById(param.getResourceId());
        return resource;
    }

    @Override
    public Page<Resource> queryChild(ResourceParam.QueryChild param) {
        Resource resource = resourceDao.getResourceById(param.getResourceId());
        if (resource == null) {
            throw new CNCException(ResultMessage.RESOURCE_NOT_EXIST);
        }
        PageRequest pageRequest = PageRequest.of(param.getPageNumber() - 1, param.getPageSize());
        Page<Resource> resourcePage = resourceDao.findAll(param, pageRequest);
        return resourcePage;
    }

    @Override
    public Resource from(CNCCreateParam param) {
        Resource parentResource = null;
        String parentResourceName = null;
        String resourceName = param.getResourceName();
        Long parentResourceId = param.getParentResourceId();
        if (parentResourceId != null) {
            parentResource = resourceDao.getResourceById(parentResourceId);
            if (parentResource == null) {
                throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_EXIST);
            }
            parentResourceName = parentResource.getResourceName();
        }
        for (String paramParentResourceName : param.getParentResourceNameArray()) {
            if (Objects.equals(parentResourceName, paramParentResourceName)) {
                Resource resource = Resource.builder().resourceName(resourceName).parentResource(parentResource).build();
                try {
                    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user && user.getResource() != null) {
                        resource.getOwnerIdSet().add(user.getResource().getId());
                    }
                } catch (Exception ignore) {
                }
                return resource;
            }
        }
        throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_MATCH);
    }
}
