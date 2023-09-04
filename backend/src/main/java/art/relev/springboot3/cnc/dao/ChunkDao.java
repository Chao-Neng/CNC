package art.relev.springboot3.cnc.dao;

import art.relev.springboot3.cnc.exclude.CNCDao;
import art.relev.springboot3.cnc.model.Chunk;
import art.relev.springboot3.cnc.model.Resource;

public interface ChunkDao extends CNCDao<Chunk> {
    Chunk getChunkByResource(Resource resource);
}
