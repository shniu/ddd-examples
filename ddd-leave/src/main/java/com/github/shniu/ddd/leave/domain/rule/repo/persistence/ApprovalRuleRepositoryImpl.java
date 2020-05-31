package com.github.shniu.ddd.leave.domain.rule.repo.persistence;

import com.github.shniu.ddd.leave.domain.rule.entity.ApprovalRule;
import com.github.shniu.ddd.leave.domain.rule.repo.ApprovalRuleRepositoryInterface;
import com.github.shniu.ddd.leave.domain.rule.repo.mapper.ApprovalRuleDao;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
public class ApprovalRuleRepositoryImpl implements ApprovalRuleRepositoryInterface {
    private ApprovalRuleDao ruleDao;

    @Override
    public int getLeaderMaxLevel(final ApprovalRule approvalRule) {
        ApprovalRule rule = ruleDao.findRule(approvalRule.getPersonType(), approvalRule.getLeaveType(), approvalRule.getDuration());
        return rule.getMaxLeaderLevel();
    }
}
