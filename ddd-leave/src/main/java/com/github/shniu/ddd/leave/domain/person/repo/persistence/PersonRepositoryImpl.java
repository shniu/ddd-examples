package com.github.shniu.ddd.leave.domain.person.repo.persistence;

import com.github.shniu.ddd.leave.domain.person.repo.facade.PersonRepository;
import com.github.shniu.ddd.leave.domain.person.repo.po.PersonPO;

/**
 * @author niushaohan
 * @date 2020/6/1 01
 */
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public PersonPO findLeaderByPersonId(String personId) {
        return null;
    }
}
