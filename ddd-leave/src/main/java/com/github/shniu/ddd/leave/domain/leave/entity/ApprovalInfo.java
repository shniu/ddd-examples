package com.github.shniu.ddd.leave.domain.leave.entity;

import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.ApprovalType;
import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.Approver;

/**
 * 审批意见
 * @author niushaohan
 * @date 2020/5/31 18
 */
public class ApprovalInfo {
    private String approvalInfoId;
    private Approver approver;
    // 审批结果：通过 or 拒绝
    private ApprovalType approvalType;
    // 审批意见描述
    private String msg;
    // 审批时间
    private long createdAt;
}
