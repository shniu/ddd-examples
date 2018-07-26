package info.chaintech.july.domain;

import info.chaintech.july.domain.enums.SendStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 邮件发送日志表
 *
 * @author shniu
 * @date 2018-07-26 下午8:45
 */

@Entity
@Table(name = "mail_log")
@EntityListeners(AuditingEntityListener.class)
@Data
public class MailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long logId;

    /**
     * 收件人邮件
     */
    @Column(length = 64)
    private String email;

    /**
     * 收件人标题
     */
    @Column(length = 128)
    private String title;

    /**
     * 收件人
     */
    private User recipient;

    /**
     * 邮件发送状态
     */
    @Enumerated(EnumType.STRING)
    private SendStatus sendStatus;

    /**
     * 重试的邮件记录, 重试就生成一条新的记录
     */
    @OneToOne()
    @JoinColumn(name = "resend_log_id", referencedColumnName = "logId")
    private MailLog resendMailLog;

    /**
     * 创建时间
     */
    @CreatedDate
    private Date createdOn;

    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private Date updatedOn;
}
