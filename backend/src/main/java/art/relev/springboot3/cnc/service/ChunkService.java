package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.model.Chunk;
import art.relev.springboot3.cnc.param.ChunkParam;

public interface ChunkService {
    Chunk create(ChunkParam.Create param);

    void delete(ChunkParam.Delete param);

    Chunk update(ChunkParam.Update param);

    Chunk query(ChunkParam.Query param);
}
