package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.ChunkDao;
import art.relev.springboot3.cnc.model.Chunk;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.ChunkParam;
import art.relev.springboot3.cnc.service.ChunkService;
import art.relev.springboot3.cnc.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChunkServiceImpl implements ChunkService {
    private final ChunkDao chunkDao;
    private final ResourceService resourceService;

    @Override
    @CachePut(value = Chunk.RESOURCE_NAME, key = "#result.resource.id")
    public Chunk create(ChunkParam.Create param) {
        Resource resource = resourceService.from(param);
        Chunk chunk = Chunk.builder().resource(resource).name(param.getName()).description(param.getDescription()).build();
        chunkDao.save(chunk);
        return chunk;
    }

    @Override
    @CacheEvict(value = Chunk.RESOURCE_NAME, key = "#param.resourceId")
    public void delete(ChunkParam.Delete param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Chunk chunk = Chunk.builder().resource(resource).build();
        chunkDao.delete(chunk);
    }

    @Override
    @CachePut(value = Chunk.RESOURCE_NAME, key = "#result.resource.id")
    public Chunk update(ChunkParam.Update param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Chunk chunk = Chunk.builder().resource(resource).name(param.getName()).description(param.getDescription()).build();
        chunk = chunkDao.save(chunk);
        return chunk;
    }

    @Override
    @Cacheable(value = Chunk.RESOURCE_NAME, key = "#param.resourceId")
    public Chunk query(ChunkParam.Query param) {
        Resource resource = Resource.builder().id(param.getResourceId()).build();
        Chunk chunk = chunkDao.getChunkByResource(resource);
        return chunk;
    }
}
