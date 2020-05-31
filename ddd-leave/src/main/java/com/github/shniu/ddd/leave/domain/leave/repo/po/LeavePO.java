package com.github.shniu.ddd.leave.domain.leave.repo.po;

import com.github.shniu.ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
@Entity
@Table(name = "leave")
@Setter
@Getter
public class LeavePO {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String applicantId;
    private String applicantName;
    private PersonType personType;

    @Transient
    private List<ApprovalInfoPO> historyApprovalInfoPOs;
}
