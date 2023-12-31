package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCCreateParam;
import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class RoleParam {
    private static final String RESOURCE_NAME = Role.RESOURCE_NAME;
    private static final String[] PARENT_RESOURCE_NAME_ARRAY = Role.PARENT_RESOURCE_NAME_ARRAY;

    @Data
    private static abstract class AbstractParam implements CNCParam {
        @JsonIgnore
        private String resourceName = RESOURCE_NAME;
        @JsonIgnore
        private String[] parentResourceNameArray = PARENT_RESOURCE_NAME_ARRAY;
    }

    @Data
    @Schema(name = "CreateRoleParam", description = "创建角色参数")
    public static class Create extends AbstractParam implements CNCCreateParam {
        private static final String AUTHORITY_NAME = "create";
        @NotBlank(message = "角色名称不能为空")
        @Length(max = 64, message = "角色名称长度超过64")
        @Schema(description = "角色名称")
        private String name;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
        @JsonIgnore
        private Long parentResourceId;
    }

    @Data
    @Schema(name = "DeleteRoleParam", description = "删除角色参数")
    public static class Delete extends AbstractParam {
        private static final String AUTHORITY_NAME = "delete";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "UpdateRoleParam", description = "修改角色参数")
    public static class Update extends AbstractParam {
        private static final String AUTHORITY_NAME = "update";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @NotBlank(message = "角色名称不能为空")
        @Length(max = 64, message = "角色名称长度超过64")
        @Schema(description = "角色名称")
        private String name;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "QueryRoleParam", description = "查询角色参数")
    public static class Query extends AbstractParam {
        private static final String AUTHORITY_NAME = "query";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }
}
