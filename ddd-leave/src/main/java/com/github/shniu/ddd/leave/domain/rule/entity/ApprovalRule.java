package com.github.shniu.ddd.leave.domain.rule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
@ToString
@Builder
@AllArgsConstructor
@Getter
public class ApprovalRule {
    private String personType;
    private String leaveType;
    private long duration;
    private int maxLeaderLevel;
}
