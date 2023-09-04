package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface ResourceDao extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
    Resource getResourceById(Long id);

    Set<Resource> getResourcesByParentResource(Resource parentResource);
}
