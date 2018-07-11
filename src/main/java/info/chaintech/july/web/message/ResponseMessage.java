package info.chaintech.july.web.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by shniu on 2018/7/9.
 */

@ToString
public class ResponseMessage<T> implements Serializable {
    @Getter
    @Setter
    protected int code;
    @Getter
    @Setter
    protected String msg;
    @Getter
    @Setter
    protected T data;
    @Getter
    @Setter
    private long timestamp;
    /**
     * 过滤字段：指定需要序列化的字段
     */
    @JsonIgnore
    @Ignore
    private transient Map<Class<?>, Set<String>> includes;
    /**
     * 过滤字段：指定不需要序列化的字段
     */
    @JsonIgnore
    @Ignore
    private transient Map<Class<?>, Set<String>> excludes;

    public ResponseMessage() {
    }

    public static <T> ResponseMessage<T> ok() {
        return new ResponseMessage<T>()
                .putTimestamp()
                .code(ResponseCode.Succeed.code())
                .msg(ResponseCode.Succeed.msg());
    }

    public static <T> ResponseMessage<T> ok(T data) {
        return new ResponseMessage<T>()
                .data(data)
                .putTimestamp()
                .code(0).msg("Succeed");
    }

    public static <T> ResponseMessage<T> error() {
        return error(ResponseCode.Failed);
    }

    public static <T> ResponseMessage<T> error(ResponseCode responseCode) {
        return new ResponseMessage<T>()
                .putTimestamp()
                .code(responseCode.code())
                .msg(responseCode.msg());
    }

    public ResponseMessage<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseMessage<T> putTimestamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public ResponseMessage<T> code(int code) {
        this.code = code;
        return this;
    }

    public ResponseMessage<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * exclude
     */
    public ResponseMessage<T> exclude(Class type, Collection<String> fields) {
        if (null == excludes) {
            excludes = new HashMap<>();
        }

        if (fields == null || fields.isEmpty()) {
            return this;
        }

        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(excludes, type).add(field);
            }
        });

        return this;
    }

    public ResponseMessage<T> exclude(Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (getData() != null) {
            type = getData().getClass();
        } else {
            return this;
        }
        exclude(type, fields);
        return this;
    }

    public ResponseMessage<T> exclude(Class type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
        return map.computeIfAbsent(type, k -> new HashSet<>());
    }
}
