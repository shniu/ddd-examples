package info.chaintech.july.web.error;

import info.chaintech.july.commons.utils.ResponseUtil;
import info.chaintech.july.web.message.ResponseCode;
import info.chaintech.july.web.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author shniu
 * @date 2018-07-11 下午8:10
 * @email niushaohan@digcredit.com
 */

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public void methodArgumentNotValidException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // log.error("系统异常统一处理", ex);

        // 统一处理参数不合法异常
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException c = (MethodArgumentNotValidException) ex;
            List<ObjectError> errors = c.getBindingResult().getAllErrors();
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("\n\t");
            errors.forEach(x -> errorMsg.append(x.toString()).append("\n\t"));
            log.warn("请求参数不合法, 异常信息: {}", errorMsg);

            ResponseUtil.writeResponse(response, ResponseMessage.error(ResponseCode.ParamsNotValid));
            // populateExceptionResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, errorMsg.toString());
        } else {  // swallow 其他异常
            log.error("系统异常, 异常信息: {}", ex.getMessage(), ex);
            ResponseUtil.writeResponse(response, ResponseMessage.error());
        }
    }

}
