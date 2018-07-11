package info.chaintech.july.service.impl;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.enums.PipelineStatus;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.dto.BizLineDto;
import info.chaintech.july.web.vo.NewBizLineVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by shniu on 2018/7/9.
 */

@AllArgsConstructor
@Slf4j
public class BusinessLineServiceImpl implements BusinessLineService {

    private BusinessPipelineRepository businessPipelineRepository;

    @Override
    public List<BizLineDto> queryBizLinesPageable(Pageable pageable) {
        return null;
    }

    @Override
    public void addBizPipeline(NewBizLineVo newBizLineVo) {
        BusinessPipeline businessPipeline = new BusinessPipeline();
        businessPipeline.setTopic(newBizLineVo.getTopic());
        businessPipeline.setStatus(PipelineStatus.valueOf(newBizLineVo.getStatus()));
        businessPipeline.setInChargeUser(newBizLineVo.getInChargeUser());
        businessPipeline.setCreatedUser(null);  // Todo
        businessPipelineRepository.save(businessPipeline);
    }
}
