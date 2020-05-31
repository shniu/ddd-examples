package com.github.shniu.ddd.leave.domain.rule.repo.po;

import com.github.shniu.ddd.leave.domain.leave.entity.valueobject.LeaveType;
import com.github.shniu.ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
@Entity
@Table(name = "approval_rule")
@Data
public class ApprovalRulePO {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private LeaveType leaveType;
    private PersonType personType;
    private long duration;
    private String applicantRoleId;
    private int leaderMaxLevel;
}
