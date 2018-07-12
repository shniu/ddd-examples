package info.chaintech.july.service.dto;

import lombok.Data;

@Data
public class PipeTodoDto {
    private long tid;
    private String content;
    private String todoStatus;
    private String statusDesc;
    private String createdOn;
}
