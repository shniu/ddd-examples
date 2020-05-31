package com.github.shniu.ddd.leave.interfaces.assembler;

import com.github.shniu.ddd.leave.domain.leave.entity.Leave;
import com.github.shniu.ddd.leave.interfaces.dto.LeaveDTO;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
public class LeaveAssembler {
    public static Leave toDO(LeaveDTO leaveDTO) {
        // todo
        return new Leave();
    }
}
