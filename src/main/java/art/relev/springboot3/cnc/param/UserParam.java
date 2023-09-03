package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import art.relev.springboot3.cnc.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UserParam {
    private static final String RESOURCE_NAME = User.RESOURCE_NAME;

    @Data
    @Schema(name = "LoginUserParam", description = "登录用户参数")
    public static class Login implements CNCParam {
        private static final String AUTHORITY_NAME = "login";
        @Schema(description = "用户名称")
        @NotBlank(message = "用户名不能为空")
        private String name;
        @Schema(description = "用户密码")
        @NotBlank(message = "密码不能为空")
        private String password;

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "RegisterUserParam", description = "注册用户参数")
    public static class Register implements CNCParam {
        private static final String AUTHORITY_NAME = "register";
        @Schema(description = "用户名称")
        @NotBlank(message = "用户名不能为空")
        private String name;
        @Schema(description = "用户密码")
        @NotBlank(message = "密码不能为空")
        private String password;

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "LogoutUserParam", description = "注册用户参数")
    public static class Logout implements CNCParam {
        private static final String AUTHORITY_NAME = "register";

        @Override
        @JsonIgnore
        public Long getResourceId() {
            return null;
        }

        @Override
        @JsonIgnore
        public String getResourceName() {
            return RESOURCE_NAME;
        }

        @Override
        @JsonIgnore
        public String getAuthorityName() {
            return AUTHORITY_NAME;
        }

        @Override
        @JsonIgnore
        public Long getParentResourceId() {
            return null;
        }
    }
}
