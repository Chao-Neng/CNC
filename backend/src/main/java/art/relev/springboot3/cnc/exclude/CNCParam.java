package art.relev.springboot3.cnc.exclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CNCParam {
    Long getResourceId();

    String getResourceName();

    @JsonIgnore
    default String getEndpointName() {
        return this.getResourceName();
    }

    String getAuthorityName();

    String[] getParentResourceNameArray();
}
