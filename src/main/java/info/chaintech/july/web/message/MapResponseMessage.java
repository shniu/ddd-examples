package info.chaintech.july.web.message;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by shniu on 2018/7/9.
 */
public class MapResponseMessage extends ResponseMessage<Map<String, Object>> {
    public MapResponseMessage() {
        data(new LinkedHashMap<>());
    }

    public static MapResponseMessage ok() {
        return ok("Succeed");
    }

    public static MapResponseMessage ok(String msg) {
        MapResponseMessage responseMessage = new MapResponseMessage();
        responseMessage.code = 0;
        responseMessage.msg = msg;
        responseMessage.putTimestamp();
        return responseMessage;
    }

    public static MapResponseMessage error() {
        return error(ResponseCode.Failed);
    }

    public static MapResponseMessage error(ResponseCode responseCode) {
        MapResponseMessage mapResponseMessage = new MapResponseMessage();
        mapResponseMessage.msg = responseCode.msg();
        mapResponseMessage.code = responseCode.code();
        mapResponseMessage.putTimestamp();
        return mapResponseMessage;
    }

    public MapResponseMessage put(String key, Object value) {
        data.put(key, value);
        return this;
    }
}
