package com.github.shniu.ddd.leave.domain.leave.entity;

import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.Applicant;
import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.Approver;
import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.LeaveType;
import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author niushaohan
 * @date 2020/5/31 18
 */
@Getter
@ToString
public class Leave {
    private String id;
    // 请假人
    private Applicant applicant;
    // 审批人
    @Setter
    private Approver approver;
    // 请假类型
    private LeaveType leaveType;
    // 请假状态
    @Setter
    private Status status;
    // 请假开始时间
    private Date startTime;
    // 请假结束时间
    private Date endTime;
    // 请假时长
    private long duration;
    // 审批领导的最高级别
    @Setter
    private int leaderMaxLevel;
    // 当前审批信息
    private ApprovalInfo currApprovalInfo;
    private List<ApprovalInfo> historyApprovalInfos;

    public long getDuration() {
        Assert.isTrue(endTime.getTime() > startTime.getTime(), "endTime should gt startTime.");

        return endTime.getTime() - startTime.getTime();
    }

    public Leave create() {
        this.setStatus(Status.APPROVING);
        this.startTime = new Date();
        return this;
    }
}
