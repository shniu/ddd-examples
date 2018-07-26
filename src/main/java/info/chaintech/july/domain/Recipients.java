package info.chaintech.july.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 邮件收件人列表
 *
 * @author shniu
 * @date 2018-07-26 下午3:33
 */

@Entity
@Table(name = "recipients")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Recipients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tid;

    /**
     * 收件人邮箱
     */
    @Column(length = 128)
    private String email;

    /**
     * 收件人姓名
     */
    @Column(length = 32)
    private String name;

    /**
     * 添加人
     */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;

    /**
     * 是否可用
     */
    private boolean enabled;

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
