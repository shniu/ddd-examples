package com.github.shniu.ddd.leave.domain.leave.service;

import com.github.shniu.ddd.leave.domain.leave.entity.Leave;
import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.Approver;
import com.github.shniu.ddd.leave.domain.leave.repo.facade.LeaveRepositoryInterface;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niushaohan
 * @date 2020/5/31 22
 */
public class LeaveDomainService {
    private LeaveRepositoryInterface leaveRepositoryInterface;
    private LeaveFactory leaveFactory;

    @Transactional
    public void createLeave(Leave leave, int leaderMaxLevel, Approver approver) {
        leave.setLeaderMaxLevel(leaderMaxLevel);
        leave.setApprover(approver);
        leave.create();

        leaveRepositoryInterface.save(leaveFactory.createLeavePO(leave));

    }
}
