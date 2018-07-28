package info.chaintech.july.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shniu
 * @date 2018-07-26 下午9:03
 * @email niushaohan@digcredit.com
 */

@Data
public class PendingMailDto {
    private String to;
    private String title;
    private List<Map<String, Object>> bizEmailDtoList = new ArrayList<>();

    /*@Data
    public class BizEmailDto {
        private String topic;
        private String status;
        private List<String> todoList = new ArrayList<>();
    }*/
}
