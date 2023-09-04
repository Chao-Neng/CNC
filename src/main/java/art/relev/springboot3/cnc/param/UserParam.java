package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UserParam {
    private static final String RESOURCE_NAME = User.RESOURCE_NAME;
    private static final String[] PARENT_RESOURCE_NAME_ARRAY = User.PARENT_RESOURCE_NAME_ARRAY;

    @Data
    private static abstract class AbstractParam implements CNCParam {
        @JsonIgnore
        private String resourceName = RESOURCE_NAME;
        @JsonIgnore
        private String[] parentResourceNameArray = PARENT_RESOURCE_NAME_ARRAY;
    }

    @Data
    @Schema(name = "LoginUserParam", description = "登录用户参数")
    public static class Login extends AbstractParam {
        private static final String AUTHORITY_NAME = "login";
        @Schema(description = "用户名称")
        @NotBlank(message = "用户名不能为空")
        private String name;
        @Schema(description = "用户密码")
        @NotBlank(message = "密码不能为空")
        private String password;
        @JsonIgnore
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "RegisterUserParam", description = "注册用户参数")
    public static class Register extends AbstractParam {
        private static final String AUTHORITY_NAME = "register";
        @Schema(description = "用户名称")
        @NotBlank(message = "用户名不能为空")
        private String name;
        @Schema(description = "用户密码")
        @NotBlank(message = "密码不能为空")
        private String password;
        @JsonIgnore
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }

    @Data
    @Schema(name = "LogoutUserParam", description = "登出用户参数")
    public static class Logout extends AbstractParam {
        private static final String AUTHORITY_NAME = "logout";
        @JsonIgnore
        private Long resourceId;
        @JsonIgnore
        private String authorityName = AUTHORITY_NAME;
    }
}
