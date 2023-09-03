package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class ArticleParam {
    @Data
    @Schema(name = "CreateArticleParam", description = "创建文章参数")
    public static class Create implements CNCParam {
        @NotBlank(message = "文章标题不能为空")
        @Length(max = 64, message = "文章标题长度超过64")
        @Schema(description = "文章标题")
        private String title;
        @NotBlank(message = "文章内容不能为空")
        @Schema(description = "文章内容")
        private String content;
        @NotNull(message = "文章所属资源不能为空")
        @Schema(description = "文章所属资源ID")
        private Long parentResourceId;

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "DeleteArticleParam", description = "删除文章参数")
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
    @Schema(name = "UpdateArticleParam", description = "修改文章参数")
    public static class Update implements CNCParam {
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @NotBlank(message = "文章标题不能为空")
        @Length(max = 64, message = "文章标题长度超过64")
        @Schema(description = "文章标题")
        private String title;
        @NotBlank(message = "文章内容不能为空")
        @Schema(description = "文章内容")
        private String content;

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "QueryArticleParam", description = "查询文章参数")
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
