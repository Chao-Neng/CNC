package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class CommentParam {
    @Data
    @Schema(name = "CreateCommentParam", description = "创建评论参数")
    public static class Create implements CNCParam {
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
    }

    @Data
    @Schema(name = "DeleteCommentParam", description = "删除评论参数")
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
    @Schema(name = "UpdateCommentParam", description = "修改评论参数")
    public static class Update implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @NotBlank(message = "评论内容不能为空")
        @Schema(description = "评论内容")
        private String content;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "QueryCommentParam", description = "查询评论参数")
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
