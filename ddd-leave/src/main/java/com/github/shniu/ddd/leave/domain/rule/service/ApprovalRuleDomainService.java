package com.github.shniu.ddd.leave.domain.rule.service;

import com.github.shniu.ddd.leave.domain.rule.entity.ApprovalRule;
import com.github.shniu.ddd.leave.domain.rule.repo.ApprovalRuleRepositoryInterface;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
public class ApprovalRuleDomainService {

    private ApprovalRuleRepositoryInterface approvalRuleRepositoryInterface;

    public ApprovalRuleDomainService(ApprovalRuleRepositoryInterface approvalRuleRepositoryInterface) {
        this.approvalRuleRepositoryInterface = approvalRuleRepositoryInterface;
    }

    /**
     * 根据用户类型，请假类型，时长查询最高审批人等级
     *
     * @param personType 用户类型
     * @param leaveType  请假类型
     * @param duration   请假时长
     * @return 审批人最高等级
     */
    public int getLeaderMaxLevel(String personType, String leaveType, long duration) {
        ApprovalRule approvalRule = ApprovalRule.builder()
                .personType(personType)
                .leaveType(leaveType)
                .duration(duration)
                .build();
        return approvalRuleRepositoryInterface.getLeaderMaxLevel(approvalRule);
    }
}
