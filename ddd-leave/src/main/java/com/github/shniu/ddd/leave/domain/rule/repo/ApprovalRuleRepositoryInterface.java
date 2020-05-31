package com.github.shniu.ddd.leave.domain.rule.repo;

import com.github.shniu.ddd.leave.domain.rule.entity.ApprovalRule;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
public interface ApprovalRuleRepositoryInterface {
    int getLeaderMaxLevel(ApprovalRule approvalRule);
}
