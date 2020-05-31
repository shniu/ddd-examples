package com.github.shniu.ddd.leave.domain.person.repo.facade;

import com.github.shniu.ddd.leave.domain.person.repo.po.PersonPO;

/**
 * @author niushaohan
 * @date 2020/6/1 01
 */
public interface PersonRepository {
    PersonPO findLeaderByPersonId(String personId);
}
