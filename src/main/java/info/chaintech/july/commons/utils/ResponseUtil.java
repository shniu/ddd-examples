package info.chaintech.july.commons.utils;

import com.google.common.base.Charsets;
import info.chaintech.july.web.message.ResponseMessage;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shniu
 * @date 2018-07-11 下午8:11
 * @email niushaohan@digcredit.com
 */

public class ResponseUtil {

    public static void writeResponse(HttpServletResponse response, ResponseMessage respModel) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        String respString = modelMapper.writeString(respModel);
        // response.setStatus(respModel.getCode());
        response.setCharacterEncoding(Charsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(respString);
        response.getWriter().flush();
    }
}
