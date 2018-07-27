package info.chaintech.july.service.impl;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import info.chaintech.july.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author shniu
 * @date 2018/7/24
 */

@Slf4j
public class EmailServiceImpl implements EmailService {

    private String from;
    private JavaMailSender javaMailSender;
    private FreeMarkerConfigurer configurer;

    private static final String ENCODING = "utf-8";

    public EmailServiceImpl(String from, JavaMailSender javaMailSender, FreeMarkerConfigurer configurer) {
        this.from = from;
        this.javaMailSender = javaMailSender;
        this.configurer = configurer;
    }

    @Override
    public void sendMail(String sendTo, String subject, List<?> content) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, ENCODING);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(MimeUtility.encodeText(subject, ENCODING, "B"));

            Template template = configurer.getConfiguration().getTemplate("message.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, new HashMap<String, Object>(){{
                put("bizLines", content);
            }});

            helper.setText(text, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | TemplateException | IOException e) {
            log.error(e.getMessage(), e);
        }

    }
}
