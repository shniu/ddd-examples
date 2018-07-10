package info.chaintech.july.service.impl;

import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.dto.BizLineDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by shniu on 2018/7/9.
 */

@Slf4j
public class BusinessLineServiceImpl implements BusinessLineService {
    @Override
    public List<BizLineDto> queryBizLinesPageable(Pageable pageable) {
        return null;
    }
}
