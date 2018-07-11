package info.chaintech.july.service.dto;

import lombok.Data;

/**
 * Created by shniu on 2018/7/9.
 */

@Data
public class BizLineDto {
    private long bizId;
    private String topic;
    private String inChargeUser;
    private String status;
    private String createdOn;
}
