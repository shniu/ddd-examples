package info.chaintech.july.conf;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.dao.PipeTodoRepository;
import info.chaintech.july.dao.UserRepository;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.EmailService;
import info.chaintech.july.service.PipeTodoService;
import info.chaintech.july.service.UserService;
import info.chaintech.july.service.impl.BusinessLineServiceImpl;
import info.chaintech.july.commons.utils.ModelMapper;
import info.chaintech.july.service.impl.EmailServiceImpl;
import info.chaintech.july.service.impl.PipeTodoServiceImpl;
import info.chaintech.july.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

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

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public EmailService emailService(JavaMailSender javaMailSender) {
        return new EmailServiceImpl(javaMailSender);
    }
}
