package info.chaintech.july.conf;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.impl.BusinessLineServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shniu
 * @date 2018-07-10 下午1:03
 * @email niushaohan@digcredit.com
 */

@Configuration
public class JulyConfig {

    @Bean
    public BusinessLineService businessLineService(BusinessPipelineRepository businessPipelineRepository) {
        return new BusinessLineServiceImpl(businessPipelineRepository);
    }
}
