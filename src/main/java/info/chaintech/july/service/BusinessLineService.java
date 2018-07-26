package info.chaintech.july.service;

import info.chaintech.july.service.dto.BizLineDto;
import info.chaintech.july.service.dto.BizPipelinesPageableDto;
import info.chaintech.july.service.dto.PendingMailDto;
import info.chaintech.july.web.vo.NewBizLineVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author shniu
 * @date 2018/7/9
 */

public interface BusinessLineService {

    /**
     * 查询商务线列表
     *
     * @param pageable 分页信息
     * @return page
     */
    BizPipelinesPageableDto queryBizLinesPageable(Pageable pageable);

    /**
     * 添加一条商务线
     * @param newBizLineVo new pipeline
     */
    void addBizPipeline(NewBizLineVo newBizLineVo);

    /**
     * 删除商务线
     *
     * @param bizId bizId
     */
    void deleteBizLine(long bizId);

    /**
     * 生成邮件列表
     *
     * @return pending emails
     */
    List<PendingMailDto> pendingEmails();
}
