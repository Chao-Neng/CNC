package art.relev.springboot3.cnc.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
@Schema(description = "统一响应体")
public class Result<T> {
    @Schema(description = "响应代码")
    private final Integer code;
    @Schema(description = "响应消息")
    private final String message;
    @Schema(description = "响应数据")
    private T data;
}
