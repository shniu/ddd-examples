package com.github.shniu.ddd.leave.domain.leave.repo.facade;

import com.github.shniu.ddd.leave.domain.leave.repo.po.LeavePO;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
public interface LeaveRepositoryInterface {
    void save(LeavePO leavePO);
}
