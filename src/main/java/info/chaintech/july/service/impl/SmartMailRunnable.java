package info.chaintech.july.service.impl;

import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.EmailService;
import info.chaintech.july.service.dto.PendingMailDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
        log.info("成功发送一封邮件, emailService={}, businessLineService={}", emailService, businessLineService);
        // 获取邮件列表
        List<PendingMailDto> pendingMails = businessLineService.pendingMailBusinessLines();
        // 执行发送邮件
        // CompletableFuture completableFuture;

        pendingMails.forEach(pendingMailDto -> {
            emailService.sendMail(
                    pendingMailDto.getTo(), pendingMailDto.getTitle(), pendingMailDto.getBizEmailDtoList()
            );

            // 记录邮件发送结果
        });

    }

}
