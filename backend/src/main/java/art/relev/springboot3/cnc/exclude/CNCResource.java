package art.relev.springboot3.cnc.exclude;

import art.relev.springboot3.cnc.model.Resource;

import java.io.Serializable;

public interface CNCResource extends Serializable {
    Long getId();

    Resource getResource();
}
