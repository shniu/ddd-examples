package com.github.shniu.ddd.leave.domain.leave.service;

import com.github.shniu.ddd.leave.domain.leave.entity.Leave;
import com.github.shniu.ddd.leave.domain.leave.repo.po.LeavePO;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
public class LeaveFactory {

    public LeavePO createLeavePO(Leave leave) {
        return new LeavePO();
    }
}
