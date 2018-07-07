package info.chaintech.july.domain;

import info.chaintech.july.domain.enums.PipelineStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by shniu on 2018/7/7.
 *
 * 商务线model
 */

@Entity
@Table(name = "business_pipeline")
@Data
public class BusinessPipeline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bizId;

    /**
     *  商务线主题
     */
    private String topic;

    // 负责人, TODO
    @Column(length = 64)
    private String inChargeUser;

    // 进展情况（随时添加，有多个）
    @OneToMany(mappedBy = "businessPipeline", fetch = FetchType.LAZY)
    private List<PipeProgress> pipeProgresses;

    /**
     * 商务线的待办列表
     */
    @OneToMany(mappedBy = "businessPipeline", fetch = FetchType.LAZY)
    private List<PipeTodo> pipeTodoList;

    /**
     * 商务线状态
     */
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private PipelineStatus status;

    /**
     * 添加时间
     */
    private Date createdOn;

    /**
     * 最后更新时间
     */
    private Date updatedOn;
}
