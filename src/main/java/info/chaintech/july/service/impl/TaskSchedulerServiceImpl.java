package info.chaintech.july.service.impl;

import info.chaintech.july.service.EmailService;
import info.chaintech.july.service.TaskSchedulerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 启动的时候可以检测是否开启了调度服务
 *
 * @author shniu
 * @date 2018-07-26 下午6:15
 */

@Slf4j
public class TaskSchedulerServiceImpl implements TaskSchedulerService {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();

    public TaskSchedulerServiceImpl(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Override
    public String startCron(Runnable runnable, String cron) {
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(cron));
        String schedulerId = "";
        scheduledFutureMap.put(schedulerId, schedule);
        return schedulerId;
    }

    @Override
    public boolean stopCron(String schedulerId) {
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.getOrDefault(schedulerId, null);
        return null != scheduledFuture && scheduledFuture.cancel(true);
    }
}
