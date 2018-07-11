package info.chaintech.july.domain;

import info.chaintech.july.domain.enums.TodoStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shniu on 2018/7/7.
 */

@Entity
@Table(name = "pipe_todo")
@EntityListeners(AuditingEntityListener.class)
@Data
public class PipeTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tid;

    /**
     * 商务线
     */
    @ManyToOne(targetEntity = BusinessPipeline.class)
    @JoinColumn(name = "biz_id", referencedColumnName = "bizId")
    private BusinessPipeline businessPipeline;

    /**
     * content
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 待办状态
     */
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus;

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
