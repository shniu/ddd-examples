package info.chaintech.july.service;

/**
 * Created by shniu on 2018/7/24.
 */
public interface EmailService {

    /**
     * 发送邮件
     *
     * @param sendTo  收件人
     * @param title   邮件标题
     * @param content 邮件正文
     */
    void sendMail(String sendTo, String title, String content);
}
