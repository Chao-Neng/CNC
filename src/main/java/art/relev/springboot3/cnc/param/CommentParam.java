package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class CommentParam {
    private static final String RESOURCE_NAME = Comment.RESOURCE_NAME;

    @Data
    @Schema(name = "CreateCommentParam", description = "创建评论参数")
    public static class Create implements CNCParam {
        private static final String AUTHORITY_NAME = "create";
        @NotBlank(message = "评论内容不能为空")
        @Schema(description = "评论内容")
        private String content;
        @NotNull(message = "评论所属资源不能为空")
        @Schema(description = "评论所属资源ID")
        private Long parentResourceId;

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }
    }

    @Data
    @Schema(name = "DeleteCommentParam", description = "删除评论参数")
    public static class Delete implements CNCParam {
        private static final String AUTHORITY_NAME = "delete";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

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
    @Schema(name = "UpdateCommentParam", description = "修改评论参数")
    public static class Update implements CNCParam {
        private static final String AUTHORITY_NAME = "update";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @NotBlank(message = "评论内容不能为空")
        @Schema(description = "评论内容")
        private String content;

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

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
    @Schema(name = "QueryCommentParam", description = "查询评论参数")
    public static class Query implements CNCParam {
        private static final String AUTHORITY_NAME = "query";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

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
