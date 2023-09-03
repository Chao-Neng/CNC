package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Chunk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class ChunkParam {
    private static final String RESOURCE_NAME = Chunk.RESOURCE_NAME;
    private static final String[] PARENT_RESOURCE_NAME_LIST = Chunk.PARENT_RESOURCE_NAME_LIST;

    private static abstract class AbstractParam implements CNCParam {
        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

        @Override
        @JsonIgnore
        public String[] getParentResourceNameList() {
            return PARENT_RESOURCE_NAME_LIST;
        }
    }

    @Data
    @Schema(name = "CreateChunkParam", description = "创建板块参数")
    public static class Create extends AbstractParam {
        private static final String AUTHORITY_NAME = "create";
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

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }
    }

    @Data
    @Schema(name = "DeleteChunkParam", description = "删除板块参数")
    public static class Delete extends AbstractParam {
        private static final String AUTHORITY_NAME = "delete";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "UpdateChunkParam", description = "修改板块参数")
    public static class Update extends AbstractParam {
        private static final String AUTHORITY_NAME = "update";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @Schema(description = "板块名称")
        private String name;
        @Schema(description = "板块描述")
        private String description;

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "QueryChunkParam", description = "查询板块参数")
    public static class Query extends AbstractParam {
        private static final String AUTHORITY_NAME = "query";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }
}
