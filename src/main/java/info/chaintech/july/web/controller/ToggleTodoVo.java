package info.chaintech.july.web.controller;

import lombok.Data;

@Data
public class ToggleTodoVo {
    private long tid;
    private boolean completed;
}
