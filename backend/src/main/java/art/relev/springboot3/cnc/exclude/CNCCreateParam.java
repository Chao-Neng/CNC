package art.relev.springboot3.cnc.exclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CNCCreateParam extends CNCParam {
    @Override
    @JsonIgnore
    default Long getResourceId() {
        return null;
    }

    Long getParentResourceId();
}
