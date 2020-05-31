package com.github.shniu.ddd.leave.domain.person.repo.po;

import com.github.shniu.ddd.leave.domain.person.entity.valueobject.PersonStatus;
import com.github.shniu.ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author niushaohan
 * @date 2020/6/1 01
 */
@Data
@Entity
@Table(name = "person")
public class PersonPO {
    @Id
    private String personId;

    private String personName;
    private String departmentId;
    @Enumerated(EnumType.STRING)
    private PersonType personType;
    @Transient
    private String leaderId;
    private int roleLevel;
    private Date createdAt;
    private Date lastModifyAt;
    @Enumerated(EnumType.STRING)
    private PersonStatus status;
    @OneToOne
    private RelationshipPO relationshipPO;
}
