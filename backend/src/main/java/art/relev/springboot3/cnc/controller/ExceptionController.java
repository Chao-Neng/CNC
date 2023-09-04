package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.response.Result;
import art.relev.springboot3.cnc.response.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception exception) {
        // log.error("Exception: " + exception.getMessage());
        exception.printStackTrace();
        return ResultMessage.ERROR.build();
    }

    @ExceptionHandler(CNCException.class)
    public Result<?> appException(CNCException cncException) {
        ResultMessage resultMessage = cncException.getResultMessage();
        Result<?> result = resultMessage.build();
        // log.warn("CNCException: " + result.getMessage());
        cncException.printStackTrace();
        return result;
    }
}
