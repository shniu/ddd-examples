package com.github.shniu.ddd.leave.domain.leave.entity.valueobject;

import com.github.shniu.ddd.leave.domain.person.entity.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author niushaohan
 * @date 2020/5/31 18
 */
@Builder
@Getter
@ToString
public class Approver {
    private String personId;
    private String personName;
    private int level;

    public static Approver fromPerson(Person person) {
        return Approver.builder()
                .personId(person.getPersonId())
                .personName(person.getPersonName())
                .level(person.getRoleLevel())
                .build();
    }
}
