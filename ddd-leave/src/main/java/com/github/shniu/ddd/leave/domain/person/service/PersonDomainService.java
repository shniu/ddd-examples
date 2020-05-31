package com.github.shniu.ddd.leave.domain.person.service;

import com.github.shniu.ddd.leave.domain.person.entity.Person;
import com.github.shniu.ddd.leave.domain.person.repo.facade.PersonRepository;
import com.github.shniu.ddd.leave.domain.person.repo.po.PersonPO;

import java.util.Optional;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
public class PersonDomainService {

    private PersonRepository personRepository;
    private PersonFactory personFactory;

    public PersonDomainService(PersonRepository personRepository, PersonFactory personFactory) {
        this.personRepository = personRepository;
        this.personFactory = personFactory;
    }

    /**
     * 查找下一个审批人
     *
     * @param personId
     * @param leaderMaxLevel
     * @return
     */
    public Person findFirstApprover(String personId, int leaderMaxLevel) {
        PersonPO leaderPO = personRepository.findLeaderByPersonId(personId);
        return Optional.ofNullable(leaderPO)
                .filter(personPO -> personPO.getRoleLevel() <= leaderMaxLevel)
                .map(personPO -> personFactory.createPerson(personPO))
                .orElse(null);
    }
}
