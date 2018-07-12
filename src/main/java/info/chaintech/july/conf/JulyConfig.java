package info.chaintech.july.conf;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.dao.PipeTodoRepository;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.PipeTodoService;
import info.chaintech.july.service.impl.BusinessLineServiceImpl;
import info.chaintech.july.commons.utils.ModelMapper;
import info.chaintech.july.service.impl.PipeTodoServiceImpl;
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
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BusinessLineService businessLineService(BusinessPipelineRepository businessPipelineRepository) {
        return new BusinessLineServiceImpl(businessPipelineRepository);
    }

    @Bean
    public PipeTodoService pipeTodoService(PipeTodoRepository pipeTodoRepository,
                                           BusinessPipelineRepository businessPipelineRepository) {
        return new PipeTodoServiceImpl(pipeTodoRepository, businessPipelineRepository);
    }
}
