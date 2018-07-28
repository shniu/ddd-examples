package info.chaintech.july.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author shniu
 * @date 2018/7/9
 */

@Data
public class NewBizLineVo {
    @NotEmpty
    private String topic;
    @NotEmpty
    private String status;
    private long inChargeUserId;
}
