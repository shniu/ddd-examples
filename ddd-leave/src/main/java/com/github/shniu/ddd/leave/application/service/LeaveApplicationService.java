package com.github.shniu.ddd.leave.application.service;

import com.github.shniu.ddd.leave.domain.leave.entity.Leave;
import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.Approver;
import com.github.shniu.ddd.leave.domain.leave.service.LeaveDomainService;
import com.github.shniu.ddd.leave.domain.person.entity.Person;
import com.github.shniu.ddd.leave.domain.person.service.PersonDomainService;
import com.github.shniu.ddd.leave.domain.rule.service.ApprovalRuleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
@Slf4j
public class LeaveApplicationService {
    private LeaveDomainService leaveDomainService;
    private ApprovalRuleDomainService approvalRuleDomainService;
    private PersonDomainService personDomainService;

    public LeaveApplicationService(LeaveDomainService leaveDomainService,
                                   ApprovalRuleDomainService approvalRuleDomainService,
                                   PersonDomainService personDomainService) {
        this.leaveDomainService = leaveDomainService;
        this.approvalRuleDomainService = approvalRuleDomainService;
        this.personDomainService = personDomainService;
    }

    @Transactional
    public void createLeaveInfo(Leave leave) {
        // 1. 查询审批规则和审批者的最高等级
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(
                leave.getApplicant().getPersonType(), leave.getLeaveType().name(), leave.getDuration());

        // 2. 查找审批人
        Person approver = personDomainService.findFirstApprover(leave.getApplicant().getPersonId(), leaderMaxLevel);

        // 3. 创建请假单
        leaveDomainService.createLeave(leave, leaderMaxLevel, Approver.fromPerson(approver));
    }
}
