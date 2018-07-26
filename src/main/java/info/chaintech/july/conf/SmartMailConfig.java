package info.chaintech.july.conf;

import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.EmailService;
import info.chaintech.july.service.TaskSchedulerService;
import info.chaintech.july.service.impl.SmartMailRunnable;
import info.chaintech.july.service.impl.TaskSchedulerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author shniu
 * @date 2018-07-26 下午8:01
 * @email niushaohan@digcredit.com
 */

@Configuration
@Slf4j
public class SmartMailConfig {

    @Value("${july.mail.enable}")
    public boolean mailEnable;
    @Value("${july.mail.cron}")
    public String mailCron;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.setPoolSize(8);
        return poolTaskScheduler;
    }

    @Bean
    public TaskSchedulerService taskSchedulerService(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        return new TaskSchedulerServiceImpl(threadPoolTaskScheduler);
    }

    @Bean
    public ApplicationRunner applicationRunner(TaskSchedulerService taskSchedulerService,
                                               EmailService emailService,
                                               BusinessLineService businessLineService) {
        return args -> {
            if (mailEnable) {
                log.info("July 的邮件发送调度服务已开启, 开始初始化调度服务");

                // 启动一个调度任务
                SmartMailRunnable smartMailRunnable = new SmartMailRunnable(emailService, businessLineService);
                taskSchedulerService.startCron(smartMailRunnable, mailCron);
                
                log.info("邮件发送调度服务初始化完成!");
            } else {
                log.info("July 的邮件发送调度服务已关闭");
            }
        };
    }
}
