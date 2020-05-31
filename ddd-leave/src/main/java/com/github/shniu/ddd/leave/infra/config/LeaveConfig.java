package com.github.shniu.ddd.leave.infra.config;

import com.github.shniu.ddd.leave.application.service.LeaveApplicationService;
import com.github.shniu.ddd.leave.domain.leave.service.LeaveDomainService;
import com.github.shniu.ddd.leave.domain.person.repo.facade.PersonRepository;
import com.github.shniu.ddd.leave.domain.person.repo.persistence.PersonRepositoryImpl;
import com.github.shniu.ddd.leave.domain.person.service.PersonDomainService;
import com.github.shniu.ddd.leave.domain.person.service.PersonFactory;
import com.github.shniu.ddd.leave.domain.rule.repo.ApprovalRuleRepositoryInterface;
import com.github.shniu.ddd.leave.domain.rule.repo.persistence.ApprovalRuleRepositoryImpl;
import com.github.shniu.ddd.leave.domain.rule.service.ApprovalRuleDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niushaohan
 * @date 2020/6/1 00
 */
@Configuration
public class LeaveConfig {

    @Bean
    public LeaveDomainService leaveDomainService() {
        return new LeaveDomainService();
    }

    @Bean
    public ApprovalRuleRepositoryInterface approvalRuleRepositoryInterface() {
        return new ApprovalRuleRepositoryImpl();
    }

    @Bean
    public ApprovalRuleDomainService approvalRuleDomainService(
            final ApprovalRuleRepositoryInterface approvalRuleRepositoryInterface) {
        return new ApprovalRuleDomainService(approvalRuleRepositoryInterface);
    }

    @Bean
    public PersonRepository personRepository() {
        return new PersonRepositoryImpl();
    }

    @Bean
    public PersonFactory personFactory() {
        return new PersonFactory();
    }

    @Bean
    public PersonDomainService personDomainService(
            final PersonRepository personRepository, final PersonFactory personFactory) {
        return new PersonDomainService(personRepository, personFactory);
    }

    @Bean
    public LeaveApplicationService leaveApplicationService(
            final LeaveDomainService leaveDomainService,
            final ApprovalRuleDomainService approvalRuleDomainService,
            final PersonDomainService personDomainService) {
        return new LeaveApplicationService(leaveDomainService, approvalRuleDomainService, personDomainService);
    }
}
