package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.ResourceParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
}
