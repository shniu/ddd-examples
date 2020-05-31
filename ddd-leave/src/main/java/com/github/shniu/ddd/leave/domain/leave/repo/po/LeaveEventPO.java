package com.github.shniu.ddd.leave.domain.leave.repo.po;

import com.github.shniu.ddd.leave.domain.leave.event.LeaveEventType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
@Entity
@Table(name = "leave_event")
@Data
public class LeaveEventPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private LeaveEventType leaveEventType;
    private Date timestamp;
    private String source;
    private String data;
}
