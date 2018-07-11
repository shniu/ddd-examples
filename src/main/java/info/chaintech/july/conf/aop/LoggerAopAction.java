package info.chaintech.july.conf.aop;

import info.chaintech.july.commons.utils.ModelMapper;
import info.chaintech.july.commons.utils.StringUtil;
import info.chaintech.july.web.message.ResponseMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author shniu
 * @date 2018-06-07 下午4:01
 * @email niushaohan@digcredit.com
 */

@Aspect
@Component
@Slf4j
public class LoggerAopAction {

    private ModelMapper modelMapper;

    @Autowired
    public LoggerAopAction(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // @Pointcut("execution(public * com.digcredit.cool.ju.web.controller..*(..))")
    @Pointcut("@annotation(info.chaintech.july.conf.aop.CoolLogger) || execution(public * info.chaintech.july.web.controller..*(..))")
    private void pointCutMethod() {
    }

    @Around("pointCutMethod()")
    public Object recordLogs(ProceedingJoinPoint joinPoint) {
        long startTs = System.currentTimeMillis();
        Object result;

        MarkInfo markInfo = getMarkInfo(joinPoint);
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info(">>> {} 的请求参数: {}, 请求路径: {} {}", markInfo.getRemark(), args, request.getMethod(), request.getServletPath());

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.warn("", throwable);
            result = ResponseMessage.error();
        }

        // 程序执行时间
        float executeDeltaSeconds = (float) ((System.currentTimeMillis() - startTs) / 1000.0);
        log.info(">>> {} 的请求处理完成, 共计花费 {} s, 响应: {}", markInfo.getRemark(), executeDeltaSeconds,
                modelMapper.prettify(result));
        return result;
    }

    private MarkInfo getMarkInfo(ProceedingJoinPoint joinPoint) {
        MarkInfo markInfo = new MarkInfo();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取被拦截的方法
        Method method = signature.getMethod();
        // 获取被拦截的方法名
        String methodName = method.getName();

        markInfo.setClassName(joinPoint.getTarget().getClass().getName());
        markInfo.setMethodName(methodName);

        CoolLogger coolLogger = method.getAnnotation(CoolLogger.class);
        if (coolLogger != null) {
            markInfo.setAction(coolLogger.action());
            markInfo.setRemark(coolLogger.remark());
            markInfo.setTargetType(coolLogger.targetType());
        }

        return markInfo;
    }

    @Data
    private class MarkInfo {
        private String className;
        private String methodName;
        private String remark = StringUtil.EMPTY_STRING;
        private String action = StringUtil.EMPTY_STRING;
        private String targetType = StringUtil.EMPTY_STRING;
    }
}
