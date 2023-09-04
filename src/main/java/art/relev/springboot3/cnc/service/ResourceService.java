package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.exclude.CNCCreateParam;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.ResourceParam;
import org.springframework.data.domain.Page;

public interface ResourceService {
    Resource query(ResourceParam.Query param);

    Page<Resource> queryChild(ResourceParam.QueryChild param);

    Resource from(CNCCreateParam param);
}
