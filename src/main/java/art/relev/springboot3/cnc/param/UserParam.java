package art.relev.springboot3.cnc.param;

import art.relev.springboot3.cnc.exclude.CNCParam;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class UserParam {
    @Data
    @Schema(name = "LoginUserParam", description = "登录用户参数")
    public static class Login implements CNCParam {
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
        public Long getParentResourceId() {
            return null;
        }
    }

    @Data
    @Schema(name = "RegisterUserParam", description = "注册用户参数")
    public static class Register implements CNCParam {
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
        public Long getParentResourceId() {
            return null;
        }
    }
}
