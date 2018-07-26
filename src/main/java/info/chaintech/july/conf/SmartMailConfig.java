package info.chaintech.july.conf;

import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.EmailService;
import info.chaintech.july.service.TaskSchedulerService;
import info.chaintech.july.service.impl.SmartMailRunnable;
import info.chaintech.july.service.impl.TaskSchedulerServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
            // 启动一个调度任务
            log.info("启动发送邮件的调度任务");
            SmartMailRunnable smartMailRunnable = new SmartMailRunnable(emailService, businessLineService);
            taskSchedulerService.startCron(smartMailRunnable, "0/5 * * * * *");
        };
    }
}
