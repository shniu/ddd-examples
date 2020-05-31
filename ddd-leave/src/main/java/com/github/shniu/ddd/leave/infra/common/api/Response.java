package com.github.shniu.ddd.leave.infra.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Response {
    @Builder.Default
    private ResultCode code = ResultCode.SUCCEED;
    private String message;

    public static Response ok() {
        return Response.builder().build();
    }

    public static Response failed(String msg) {
        return Response.builder()
                .code(ResultCode.FAILED)
                .message(msg)
                .build();
    }

    public enum ResultCode {
        SUCCEED,
        FAILED
    }
}
