package com.github.shniu.ddd.leave.domain.leave.entity.valueobject;

import com.github.shniu.ddd.leave.domain.person.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/5/31 19
 */
@Slf4j
public class ApproverTest {

    @Test
    public void test_fromPerson() {
        Person person = new Person("100", "张三", 3);
        Approver approver = Approver.fromPerson(person);
        assertEquals(person.getRoleLevel(), approver.getLevel());
        assertEquals(person.getPersonName(), approver.getPersonName());
        assertEquals(person.getPersonId(), approver.getPersonId());
        log.info("approver: {}", approver);
    }
}