package info.chaintech.july.service;

/**
 * @author shniu
 * @date 2018-07-26 下午5:18
 * @email niushaohan@digcredit.com
 */

public interface TaskSchedulerService {

    /**
     * 启动一个Cron调度
     *
     * @param runnable runnable
     * @param cron cron表达式
     * @return 调度的id
     */
    String startCron(Runnable runnable, String cron);

    /**
     * 停止一个Cron调度
     *
     * @param schedulerId 调度id
     * @return 是否成功
     */
    boolean stopCron(String schedulerId);
}
