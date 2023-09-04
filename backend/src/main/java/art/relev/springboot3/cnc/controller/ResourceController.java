package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.param.ResourceParam;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
@Tag(name = "resource", description = "资源相关接口")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping("/queryChild")
    @PreAuthorize("@authorityCheck.check(#param, false, true)")
    @Operation(summary = "查询子级资源", description = "查询子级资源", operationId = "queryChildResource", security = {@SecurityRequirement(name = "token")})
    public Result<Page<Resource>> queryChild(@RequestBody @Validated ResourceParam.QueryChild param) {
        Page<Resource> resourcePage = resourceService.queryChild(param);
        return ResultMessage.OK.build(resourcePage);
    }
}
