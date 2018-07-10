package info.chaintech.july.service;

import info.chaintech.july.service.dto.BizLineDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by shniu on 2018/7/9.
 */

public interface BusinessLineService {

    /**
     * 查询商务线列表
     */
    List<BizLineDto> queryBizLinesPageable(Pageable pageable);
}
