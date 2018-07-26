package info.chaintech.july.domain.enums;

/**
 * @author shniu
 */

public enum SendStatus {
    /**
     * 发送成功
     */
    Succeed,

    /**
     * 发送失败
     */
    Failed,

    /**
     * 重试, 会产生一条新的记录
     */
    Resend
}
