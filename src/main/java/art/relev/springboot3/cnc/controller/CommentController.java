package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.model.Comment;
import art.relev.springboot3.cnc.param.CommentParam;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.CommentService;
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
@RequestMapping("/comment")
@Tag(name = "comment", description = "评论相关接口")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    @PreAuthorize("@authorityCheck.check(#param, 'comment', 'create', false, true)")
    @Operation(summary = "创建评论", description = "创建评论", operationId = "createComment", security = {@SecurityRequirement(name = "token")})
    public Result<Comment> create(@RequestBody @Validated CommentParam.Create param) {
        Comment comment = commentService.create(param);
        return ResultMessage.OK.build(comment);
    }

    @PostMapping("/delete")
    @PreAuthorize("@authorityCheck.check(#param, 'comment', 'delete', true, false)")
    @Operation(summary = "删除评论", description = "删除评论", operationId = "deleteComment", security = {@SecurityRequirement(name = "token")})
    public Result<?> delete(@RequestBody @Validated CommentParam.Delete param) {
        commentService.delete(param);
        return ResultMessage.OK.build();
    }

    @PostMapping("/update")
    @PreAuthorize("@authorityCheck.check(#param, 'comment', 'update', true, false)")
    @Operation(summary = "修改评论", description = "修改评论", operationId = "updateComment", security = {@SecurityRequirement(name = "token")})
    public Result<Comment> update(@RequestBody @Validated CommentParam.Update param) {
        Comment comment = commentService.update(param);
        return ResultMessage.OK.build(comment);
    }

    @PostMapping("/query")
    @PreAuthorize("@authorityCheck.check(#param, 'comment', 'query', false, true)")
    @Operation(summary = "查询评论", description = "查询评论", operationId = "queryComment", security = {@SecurityRequirement(name = "token")})
    public Result<Comment> query(@RequestBody @Validated CommentParam.Query param) {
        Comment comment = commentService.query(param);
        return ResultMessage.OK.build(comment);
    }
}
