package info.chaintech.july.service.impl;

import info.chaintech.july.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author shniu
 * @date 2018/7/24
 */
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String sendTo, String title, String content) {

    }
}
