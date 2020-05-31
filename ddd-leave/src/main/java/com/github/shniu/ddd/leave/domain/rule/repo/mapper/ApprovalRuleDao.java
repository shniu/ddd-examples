package com.github.shniu.ddd.leave.domain.rule.repo.mapper;

import com.github.shniu.ddd.leave.domain.rule.entity.ApprovalRule;
import com.github.shniu.ddd.leave.domain.rule.repo.po.ApprovalRulePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
public interface ApprovalRuleDao extends JpaRepository<ApprovalRulePO, String> {
    @Query(value = "select r from ApprovalRulePO r where r.applicantRoleId=?1 and r.leaveType=?2 and r.duration=?3")
    ApprovalRule findRule(String applicantRoleId, String leaveType, long duration);
}
