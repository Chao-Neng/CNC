package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCCreateParam;
import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

public class ArticleParam {
    private static final String RESOURCE_NAME = Article.RESOURCE_NAME;
    private static final String[] PARENT_RESOURCE_NAME_ARRAY = Article.PARENT_RESOURCE_NAME_ARRAY;

    @Data
    private static abstract class AbstractParam implements CNCParam {
        @JsonIgnore
        private String resourceName = RESOURCE_NAME;
        @JsonIgnore
        private String[] parentResourceNameArray = PARENT_RESOURCE_NAME_ARRAY;
    }

    @Data
    @Schema(name = "CreateArticleParam", description = "创建文章参数")
    public static class Create extends AbstractParam implements CNCCreateParam {
        private static final String AUTHORITY_NAME = "create";
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
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "DeleteArticleParam", description = "删除文章参数")
    public static class Delete extends AbstractParam {
        private static final String AUTHORITY_NAME = "delete";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "UpdateArticleParam", description = "修改文章参数")
    public static class Update extends AbstractParam {
        private static final String AUTHORITY_NAME = "update";
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
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "QueryArticleParam", description = "查询文章参数")
    public static class Query extends AbstractParam {
        private static final String AUTHORITY_NAME = "query";
        @NotNull(message = "资源ID不能为空")
        @Schema(description = "资源ID")
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }
}
