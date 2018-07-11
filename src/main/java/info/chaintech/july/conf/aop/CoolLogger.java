package info.chaintech.july.conf.aop;

import java.lang.annotation.*;

/**
 * @author shniu
 * @date 2018-06-07 下午4:53
 * @email niushaohan@digcredit.com
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CoolLogger {
    String action() default "";
    String targetType() default "";
    String remark() default "";
}
