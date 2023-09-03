package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ChunkDao;
import art.relev.springboot3.cnc.dao.ResourceDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.model.Chunk;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.ChunkParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ChunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChunkServiceImpl implements ChunkService {
    private final ChunkDao chunkDao;
    private final ResourceDao resourceDao;

    @Override
    public Chunk create(ChunkParam.Create param) {
        Resource parentResource = null;
        if (param.getParentResourceId() != null) {
            parentResource = resourceDao.getResourceById(param.getParentResourceId());
            if (parentResource == null) {
                throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_EXIST);
            }
            if (!parentResource.getResourceName().equals("chunk")) {
                throw new CNCException(ResultMessage.PARENT_RESOURCE_NOT_MATCH);
            }
        }
        Resource resource = Resource.builder().resourceName("chunk").parentResource(parentResource).build();
        // TODO: temp resource ownerId
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user && user.getResource() != null) {
                resource.getOwnerIdSet().add(user.getResource().getId());
            }
        } catch (Exception ignore) {
        }
        Chunk chunk = Chunk.builder().resource(resource).name(param.getName()).description(param.getDescription()).build();
        chunkDao.save(chunk);
        return chunk;
    }

    @Override
    public void delete(ChunkParam.Delete param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Chunk chunk = Chunk.builder().resource(resource).name(param.getName()).build();
        chunkDao.delete(chunk);
    }

    @Override
    public Chunk update(ChunkParam.Update param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Chunk chunk = Chunk.builder().resource(resource).name(param.getName()).description(param.getDescription()).build();
        chunk = chunkDao.save(chunk);
        return chunk;
    }

    @Override
    public Chunk query(ChunkParam.Query param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Chunk chunk = chunkDao.getChunkByResource(resource);
        return chunk;
    }
}
