package art.relev.springboot3.cnc.exception;

import art.relev.springboot3.cnc.response.ResultMessage;

public class CNCException extends RuntimeException {
    private final ResultMessage resultMessage;

    public CNCException(ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }

    public ResultMessage getResultMessage() {
        return resultMessage;
    }
}
