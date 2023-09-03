package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.ResourceParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceDao resourceDao;

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
    public Resource from(CNCParam param) {
        Resource parentResource = null;
        String resourceName = param.getResourceName();
        Long parentResourceId = param.getParentResourceId();
        List<String> parentResourceNameList = List.of(param.getParentResourceNameList());
        if (parentResourceId == null) {
            if (!parentResourceNameList.contains(null)) {
                throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_MATCH);
            }
        } else {
            parentResource = resourceDao.getResourceById(parentResourceId);
            if (parentResource == null) {
                throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_EXIST);
            }
            if (!parentResourceNameList.contains(parentResource.getResourceName())) {
                throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_MATCH);
            }
        }
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
