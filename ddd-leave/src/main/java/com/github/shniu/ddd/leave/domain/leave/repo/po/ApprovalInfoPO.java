package com.github.shniu.ddd.leave.domain.leave.repo.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 审批意见信息.
 *
 * @author niushaohan
 * @date 2020/5/31 23
 */
@Entity
@Table(name = "approval_info")
@Data
public class ApprovalInfoPO {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String approvalInfoId;

    // 请假单id
    private String leaveId;
    // 申请人id
    private String applicantId;
    // 审核人id
    private String approverId;
    // 审批人的等级
    private int approverLevel;
    // 审核人的名字
    private String approverName;
    // 审核信息
    private String msg;
    // 审核时间
    private long createdAt;
}
