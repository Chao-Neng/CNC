package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.ResourceParam;
import org.springframework.data.domain.Page;

public interface ResourceService {
    Page<Resource> queryChild(ResourceParam.QueryChild param);
}
