package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.model.Chunk;
import art.relev.springboot3.cnc.param.ChunkParam;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ChunkService;
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
@RequestMapping("/chunk")
@Tag(name = "chunk", description = "板块相关接口")
@RequiredArgsConstructor
public class ChunkController {
    private final ChunkService chunkService;

    @PostMapping("/create")
    @PreAuthorize("@authorityCheck.check(#param, false, true)")
    @Operation(summary = "创建板块", description = "创建板块", operationId = "createChunk", security = {@SecurityRequirement(name = "token")})
    public Result<Chunk> create(@RequestBody @Validated ChunkParam.Create param) {
        Chunk chunk = chunkService.create(param);
        return ResultMessage.OK.build(chunk);
    }

    @PostMapping("/delete")
    @PreAuthorize("@authorityCheck.check(#param, true, false)")
    @Operation(summary = "删除板块", description = "删除板块", operationId = "deleteChunk", security = {@SecurityRequirement(name = "token")})
    public Result<?> delete(@RequestBody @Validated ChunkParam.Delete param) {
        chunkService.delete(param);
        return ResultMessage.OK.build();
    }

    @PostMapping("/update")
    @PreAuthorize("@authorityCheck.check(#param, true, false)")
    @Operation(summary = "修改板块", description = "修改板块", operationId = "updateChunk", security = {@SecurityRequirement(name = "token")})
    public Result<Chunk> update(@RequestBody @Validated ChunkParam.Update param) {
        Chunk chunk = chunkService.update(param);
        return ResultMessage.OK.build(chunk);
    }

    @PostMapping("/query")
    @PreAuthorize("@authorityCheck.check(#param, false, true)")
    @Operation(summary = "查询板块", description = "查询板块", operationId = "queryChunk", security = {@SecurityRequirement(name = "token")})
    public Result<Chunk> query(@RequestBody @Validated ChunkParam.Query param) {
        Chunk chunk = chunkService.query(param);
        return ResultMessage.OK.build(chunk);
    }
}
