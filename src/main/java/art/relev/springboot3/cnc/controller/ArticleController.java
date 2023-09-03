package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.model.Article;
import art.relev.springboot3.cnc.param.ArticleParam;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Tag(name = "article", description = "文章相关接口")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    @PreAuthorize("@authorityCheck.check(#param, false, true)")
    @Operation(summary = "创建文章", description = "创建文章", operationId = "createArticle", security = {@SecurityRequirement(name = "token")})
    public Result<Article> create(@RequestBody @Validated ArticleParam.Create param) {
        Article article = articleService.create(param);
        return ResultMessage.OK.build(article);
    }

    @PostMapping("/delete")
    @PreAuthorize("@authorityCheck.check(#param, true, false)")
    @Operation(summary = "删除文章", description = "删除文章", operationId = "deleteArticle", security = {@SecurityRequirement(name = "token")})
    public Result<?> delete(@RequestBody @Validated ArticleParam.Delete param) {
        articleService.delete(param);
        return ResultMessage.OK.build();
    }

    @PostMapping("/update")
    @PreAuthorize("@authorityCheck.check(#param, true, false)")
    @Operation(summary = "修改文章", description = "修改文章", operationId = "updateArticle", security = {@SecurityRequirement(name = "token")})
    public Result<Article> update(@RequestBody @Validated ArticleParam.Update param) {
        Article article = articleService.update(param);
        return ResultMessage.OK.build(article);
    }

    @PostMapping("/query")
    @PreAuthorize("@authorityCheck.check(#param, false, true)")
    @Operation(summary = "查询文章", description = "查询文章", operationId = "queryArticle", security = {@SecurityRequirement(name = "token")})
    public Result<Article> query(@RequestBody @Validated ArticleParam.Query param) {
        Article article = articleService.query(param);
        return ResultMessage.OK.build(article);
    }
}
