package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.model.Role;
import art.relev.springboot3.cnc.param.RoleParam;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.RoleService;
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
@RequestMapping("/role")
@Tag(name = "role", description = "角色相关接口")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    @PreAuthorize("@authorityCheck.check(#param, false, false)")
    @Operation(summary = "创建角色", description = "创建角色", operationId = "createRole", security = {@SecurityRequirement(name = "token")})
    public Result<Role> create(@RequestBody @Validated RoleParam.Create param) {
        Role role = roleService.create(param);
        return ResultMessage.OK.build(role);
    }

    @PostMapping("/delete")
    @PreAuthorize("@authorityCheck.check(#param, false, false)")
    @Operation(summary = "删除角色", description = "删除角色", operationId = "deleteRole", security = {@SecurityRequirement(name = "token")})
    public Result<?> delete(@RequestBody @Validated RoleParam.Delete param) {
        roleService.delete(param);
        return ResultMessage.OK.build();
    }

    @PostMapping("/update")
    @PreAuthorize("@authorityCheck.check(#param, false, false)")
    @Operation(summary = "修改角色", description = "修改角色", operationId = "updateRole", security = {@SecurityRequirement(name = "token")})
    public Result<Role> update(@RequestBody @Validated RoleParam.Update param) {
        Role role = roleService.update(param);
        return ResultMessage.OK.build(role);
    }

    @PostMapping("/query")
    @PreAuthorize("@authorityCheck.check(#param, false, false)")
    @Operation(summary = "查询角色", description = "查询角色", operationId = "queryChunk", security = {@SecurityRequirement(name = "token")})
    public Result<Role> query(@RequestBody @Validated RoleParam.Query param) {
        Role role = roleService.query(param);
        return ResultMessage.OK.build(role);
    }
}
