package info.chaintech.july.service.dto;

import lombok.Data;

/**
 * @author shniu
 * @date 2018-07-26 下午9:03
 * @email niushaohan@digcredit.com
 */

@Data
public class PendingMailDto {
    private String email;
    private String title;
    private String content;
}
