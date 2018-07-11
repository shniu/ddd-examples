package info.chaintech.july.service;

import info.chaintech.july.service.dto.BizLineDto;
import info.chaintech.july.service.dto.BizPipelinesPageableDto;
import info.chaintech.july.web.vo.NewBizLineVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by shniu on 2018/7/9.
 */

public interface BusinessLineService {

    /**
     * 查询商务线列表
     */
    BizPipelinesPageableDto queryBizLinesPageable(Pageable pageable);

    /**
     * 添加一条商务线
     * @param newBizLineVo new pipeline
     */
    void addBizPipeline(NewBizLineVo newBizLineVo);
}
