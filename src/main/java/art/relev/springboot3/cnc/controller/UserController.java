package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.param.UserParam;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "user", description = "用户相关接口")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final String TOKEN = "TOKEN";

    @PostMapping("/login")
    @PreAuthorize("@authorityCheck.check(#param, 'user', 'login', false, true)")
    @Operation(summary = "登录用户", description = "登录用户", operationId = "loginUser", security = {@SecurityRequirement(name = "token")})
    public Result<?> login(@RequestBody @Validated UserParam.Login param, HttpServletResponse response) {
        String token = userService.login(param);
        response.setHeader(TOKEN, token);
        return ResultMessage.OK.build();
    }

    @PostMapping("/register")
    @PreAuthorize("@authorityCheck.check(#param, 'user', 'register', false, true)")
    @Operation(summary = "注册用户", description = "注册用户", operationId = "registerUser", security = {@SecurityRequirement(name = "token")})
    public Result<?> register(@RequestBody @Validated UserParam.Register param, HttpServletResponse response) {
        String token = userService.register(param);
        response.setHeader(TOKEN, token);
        return ResultMessage.OK.build();
    }

    @PostMapping("/logout")
    @PreAuthorize("@authorityCheck.check(#param, 'user', 'logout', false, true)")
    @Operation(summary = "登出用户", description = "登出用户", operationId = "logoutUser", security = {@SecurityRequirement(name = "token")})
    public Result<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(TOKEN);
        userService.logout(token);
        response.setHeader(TOKEN, "");
        return ResultMessage.OK.build();
    }
}
