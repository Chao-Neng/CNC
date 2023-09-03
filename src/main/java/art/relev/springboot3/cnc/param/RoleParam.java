package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class RoleParam {
    @Data
    @Schema(name = "CreateRoleParam", description = "创建角色参数")
    public static class Create implements CNCParam {
        @NotBlank(message = "角色名称不能为空")
        @Length(max = 64, message = "角色名称长度超过64")
        @Schema(description = "角色名称")
        private String name;

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "DeleteRoleParam", description = "删除角色参数")
    public static class Delete implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "UpdateRoleParam", description = "修改角色参数")
    public static class Update implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @NotBlank(message = "角色名称不能为空")
        @Length(max = 64, message = "角色名称长度超过64")
        @Schema(description = "角色名称")
        private String name;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "QueryRoleParam", description = "查询角色参数")
    public static class Query implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }
}
