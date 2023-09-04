package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCCreateParam;
import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Chunk;
import art.relev.springboot3.cnc.model.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class CommentParam {
    private static final String RESOURCE_NAME = Comment.RESOURCE_NAME;
    private static final String[] PARENT_RESOURCE_NAME_ARRAY = Chunk.PARENT_RESOURCE_NAME_ARRAY;

    @Data
    private static abstract class AbstractParam implements CNCParam {
        @JsonIgnore
        private String resourceName = RESOURCE_NAME;
        @JsonIgnore
        private String[] parentResourceNameArray = PARENT_RESOURCE_NAME_ARRAY;
    }

    @Data
    @Schema(name = "CreateCommentParam", description = "创建评论参数")
    public static class Create extends AbstractParam implements CNCCreateParam {
        private static final String AUTHORITY_NAME = "create";
        @NotBlank(message = "评论内容不能为空")
        @Schema(description = "评论内容")
        private String content;
        @NotNull(message = "评论所属资源不能为空")
        @Schema(description = "评论所属资源ID")
        private Long parentResourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "DeleteCommentParam", description = "删除评论参数")
    public static class Delete extends AbstractParam {
        private static final String AUTHORITY_NAME = "delete";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "UpdateCommentParam", description = "修改评论参数")
    public static class Update extends AbstractParam {
        private static final String AUTHORITY_NAME = "update";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @NotBlank(message = "评论内容不能为空")
        @Schema(description = "评论内容")
        private String content;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "QueryCommentParam", description = "查询评论参数")
    public static class Query extends AbstractParam {
        private static final String AUTHORITY_NAME = "query";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }
}
