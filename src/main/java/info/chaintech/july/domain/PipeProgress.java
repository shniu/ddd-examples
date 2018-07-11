package info.chaintech.july.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shniu on 2018/7/7.
 *
 * 商务线的进展情况
 */

@Entity
@Table(name = "pipe_progress")
@EntityListeners(AuditingEntityListener.class)
@Data
public class PipeProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;

    /**
     * 进展内容
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    // 进展添加日期
    @CreatedDate
    private Date createdOn;

    @LastModifiedDate
    private Date updatedOn;

    // 跟进人
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "track_uid", referencedColumnName = "uid")
    private User trackUser;

    // 属于的商务线
    @ManyToOne(targetEntity = BusinessPipeline.class)
    @JoinColumn(name = "biz_id", referencedColumnName = "bizId")
    private BusinessPipeline businessPipeline;
}
