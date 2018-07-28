package info.chaintech.july.service.dto;

import lombok.Data;

/**
 * @author shniu
 */
@Data
public class PipeTodoDto {
    private long tid;
    private String content;
    private boolean completed;
    private String todoStatus;
    private String statusDesc;
    private String createdOn;
}
