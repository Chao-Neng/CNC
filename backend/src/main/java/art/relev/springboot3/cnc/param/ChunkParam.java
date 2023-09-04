package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCCreateParam;
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
    private static final String[] PARENT_RESOURCE_NAME_ARRAY = Chunk.PARENT_RESOURCE_NAME_ARRAY;

    @Data
    private static abstract class AbstractParam implements CNCParam {
        @JsonIgnore
        private String resourceName = RESOURCE_NAME;
        @JsonIgnore
        private String[] parentResourceNameArray = PARENT_RESOURCE_NAME_ARRAY;
    }

    @Data
    @Schema(name = "CreateChunkParam", description = "创建板块参数")
    public static class Create extends AbstractParam implements CNCCreateParam {
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
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "DeleteChunkParam", description = "删除板块参数")
    public static class Delete extends AbstractParam {
        private static final String AUTHORITY_NAME = "delete";
        @JsonIgnore(value = false)
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "UpdateChunkParam", description = "修改板块参数")
    public static class Update extends AbstractParam {
        private static final String AUTHORITY_NAME = "update";
        @JsonIgnore(value = false)
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @Schema(description = "板块名称")
        private String name;
        @Schema(description = "板块描述")
        private String description;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "QueryChunkParam", description = "查询板块参数")
    public static class Query extends AbstractParam {
        private static final String AUTHORITY_NAME = "query";
        @JsonIgnore(value = false)
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }
}
