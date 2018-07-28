package info.chaintech.july.conf;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.dao.PipeProgressRepository;
import info.chaintech.july.dao.PipeTodoRepository;
import info.chaintech.july.dao.UserRepository;
import info.chaintech.july.domain.PipeProgress;
import info.chaintech.july.service.*;
import info.chaintech.july.service.impl.*;
import info.chaintech.july.commons.utils.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author shniu
 * @date 2018-07-10 下午1:03
 */

@Configuration
public class JulyConfig {

    @Value("${spring.mail.username}")
    private String from;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BusinessLineService businessLineService(BusinessPipelineRepository businessPipelineRepository,
                                                   UserRepository userRepository) {
        return new BusinessLineServiceImpl(businessPipelineRepository, userRepository);
    }

    @Bean
    public PipeTodoService pipeTodoService(PipeTodoRepository pipeTodoRepository,
                                           BusinessPipelineRepository businessPipelineRepository) {
        return new PipeTodoServiceImpl(pipeTodoRepository, businessPipelineRepository);
    }

    @Bean
    public PipeProgressService pipeProgressService(PipeProgressRepository pipeProgressRepository,
                                                   BusinessPipelineRepository businessPipelineRepository) {
        return new PipeProgressServiceImpl(pipeProgressRepository, businessPipelineRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    /*@Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }*/

    @Bean
    public EmailService emailService(JavaMailSender javaMailSender, FreeMarkerConfigurer configurer) {
        return new EmailServiceImpl(from, javaMailSender, configurer);
    }
}
