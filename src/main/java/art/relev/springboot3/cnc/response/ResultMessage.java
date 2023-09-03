package art.relev.springboot3.cnc.response;

public enum ResultMessage {
    OK(10000, "成功"),
    ERROR(20000, "错误"),
    RESOURCE_ERROR(21000, "资源错误"),
    RESOURCE_NOT_EXIST(21001, "资源不存在"),
    PARENT_RESOURCE_ERROR(21100, "父级资源错误"),
    PARENT_RESOURCE_NOT_EXIST(21101, "父级资源不存在"),
    PARENT_RESOURCE_NOT_MATCH(21102, "父级资源不匹配"),
    USER_ERROR(29000, "用户错误"),
    USER_NOT_EXIST_ERROR(29001, "用户不存在"),
    USER_EXIST_ERROR(29002, "用户已存在"),
    USER_PASSWORD_ERROR(29003, "用户密码错误");
    private Integer code;
    private String message;

    ResultMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result<?> build() {
        return Result.of(this.code, this.message);
    }

    public <T> Result<T> build(T data) {
        return Result.of(this.code, this.message, data);
    }

}
