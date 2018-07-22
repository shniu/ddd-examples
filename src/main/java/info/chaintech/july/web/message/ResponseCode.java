package info.chaintech.july.web.message;

/**
 * Created by shniu on 2018/7/9.
 */
public enum  ResponseCode {

    Succeed(0, "Succeed"),
    Failed(-1, "Failed"),
    Unauthorized(401, "请求授权失败"),
    ParamsNotValid(402, "参数不合法, 请求不允许"),
    EntityNotExists(404, "没有发现相应资源"),  // 服务器上不再有此资源且无进一步的参考地址
    ;

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
