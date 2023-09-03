package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class ChunkParam {
    @Data
    @Schema(name = "CreateChunkParam", description = "创建板块参数")
    public static class Create implements CNCParam {
        @NotBlank(message = "板块名称不能为空")
        @Length(max = 64, message = "板块名称长度超过64")
        @Schema(description = "板块名称")
        private String name;
        @NotBlank(message = "板块描述不能为空")
        @Schema(description = "板块描述")
        private String description;
        @Schema(description = "父级资源ID")
        private Long parentResourceId;

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "DeleteChunkParam", description = "删除板块参数")
    public static class Delete implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @Schema(description = "板块名称")
        private String name;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "UpdateChunkParam", description = "修改板块参数")
    public static class Update implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @Schema(description = "板块名称")
        private String name;
        @Schema(description = "板块描述")
        private String description;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "QueryChunkParam", description = "查询板块参数")
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
