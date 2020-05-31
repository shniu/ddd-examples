package com.github.shniu.ddd.leave.domain.leave.repo.persistence;

import com.github.shniu.ddd.leave.domain.leave.repo.facade.LeaveRepositoryInterface;
import com.github.shniu.ddd.leave.domain.leave.repo.mapper.ApprovalInfoDao;
import com.github.shniu.ddd.leave.domain.leave.repo.mapper.LeaveDao;
import com.github.shniu.ddd.leave.domain.leave.repo.mapper.LeaveEventDao;
import com.github.shniu.ddd.leave.domain.leave.repo.po.LeavePO;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
public class LeaveRepositoryImpl implements LeaveRepositoryInterface {
    private LeaveDao leaveDao;
    private ApprovalInfoDao approvalInfoDao;
    private LeaveEventDao leaveEventDao;

    @Override
    public void save(LeavePO leavePO) {
        // persist leave entity
        leaveDao.save(leavePO);

        // persist approval info, set leave id for approval info
        leavePO.getHistoryApprovalInfoPOs().forEach(
                approvalInfoPO -> approvalInfoPO.setLeaveId(leavePO.getId()));
        approvalInfoDao.saveAll(leavePO.getHistoryApprovalInfoPOs());
    }
}
