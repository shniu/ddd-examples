package info.chaintech.july.service.impl;

import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shniu
 * @date 2018-07-26 下午7:50
 * @email niushaohan@digcredit.com
 */

@AllArgsConstructor
@Slf4j
public class SmartMailRunnable implements Runnable {

    private EmailService emailService;
    private BusinessLineService businessLineService;

    @Override
    public void run() {
        log.info("成功发送一封邮件");
    }

}
