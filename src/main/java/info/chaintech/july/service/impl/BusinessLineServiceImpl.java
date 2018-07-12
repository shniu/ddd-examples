package info.chaintech.july.service.impl;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.enums.PipelineStatus;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.dto.BizLineDto;
import info.chaintech.july.service.dto.BizPipelinesPageableDto;
import info.chaintech.july.web.vo.NewBizLineVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by shniu on 2018/7/9.
 */

@AllArgsConstructor
@Slf4j
public class BusinessLineServiceImpl implements BusinessLineService {

    private BusinessPipelineRepository businessPipelineRepository;

    @Override
    public BizPipelinesPageableDto queryBizLinesPageable(Pageable pageable) {
        Page<BusinessPipeline> all = businessPipelineRepository.findAllByDisabledFalse(pageable);
        BizPipelinesPageableDto bizPipelinesPageableDto = new  BizPipelinesPageableDto();
        bizPipelinesPageableDto.setTotalElements(all.getTotalElements());
        all.getContent().forEach(businessPipeline -> {
            BizLineDto bizLineDto = new BizLineDto();
            bizLineDto.setBizId(businessPipeline.getBizId());
            bizLineDto.setTopic(businessPipeline.getTopic());
            bizLineDto.setStatus(businessPipeline.getStatus().name());
            bizLineDto.setInChargeUser(businessPipeline.getInChargeUser());
            bizLineDto.setCreatedOn(DateUtils.format(businessPipeline.getCreatedOn(), "yyyy-MM-dd HH:mm:ss"));
            bizPipelinesPageableDto.addBizLineDto(bizLineDto);
        });
        return bizPipelinesPageableDto;
    }

    @Override
    @Transactional
    public void addBizPipeline(NewBizLineVo newBizLineVo) {
        BusinessPipeline businessPipeline = new BusinessPipeline();
        businessPipeline.setTopic(newBizLineVo.getTopic());
        businessPipeline.setStatus(PipelineStatus.valueOf(newBizLineVo.getStatus()));
        businessPipeline.setInChargeUser(newBizLineVo.getInChargeUser());
        businessPipeline.setCreatedUser(null);  // Todo
        businessPipeline.setDisabled(false);
        businessPipelineRepository.save(businessPipeline);
    }

    @Override
    public void deleteBizLine(long bizId) {
        Optional<BusinessPipeline> bizLine = businessPipelineRepository.findById(bizId);
        bizLine.ifPresent(businessPipeline -> {
            businessPipeline.setDisabled(true);
            businessPipelineRepository.save(businessPipeline);
        });
    }
}
