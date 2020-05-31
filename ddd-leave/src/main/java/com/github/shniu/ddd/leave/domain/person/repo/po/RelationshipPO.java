package com.github.shniu.ddd.leave.domain.person.repo.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author niushaohan
 * @date 2020/6/1 01
 */
@Entity
@Table(name = "relationship")
public class RelationshipPO {
    @Id
    private String id;
    private String personId;
    private String leaderId;
}
