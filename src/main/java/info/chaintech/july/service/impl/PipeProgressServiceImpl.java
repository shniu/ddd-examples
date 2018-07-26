package info.chaintech.july.service.impl;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.dao.PipeProgressRepository;
import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.PipeProgress;
import info.chaintech.july.service.PipeProgressService;
import info.chaintech.july.service.dto.ProgressQueryDto;
import info.chaintech.july.web.vo.NewProgressVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author shniu
 * @date 2018-07-26 下午4:09
 * @email niushaohan@digcredit.com
 */

@AllArgsConstructor
@Slf4j
public class PipeProgressServiceImpl implements PipeProgressService {

    private PipeProgressRepository pipeProgressRepository;
    private BusinessPipelineRepository businessPipelineRepository;

    @Override
    public List<ProgressQueryDto> queryProgressByBizId(long bizId) {
        pipeProgressRepository.count();
        return Collections.emptyList();
    }

    @Override
    public void addProgress(NewProgressVo newProgressDto) {
        Optional<BusinessPipeline> businessPipelineOptional = businessPipelineRepository.findById(newProgressDto.getBizId());
        businessPipelineOptional.ifPresent(businessPipeline -> {

            PipeProgress pipeProgress = new PipeProgress();
            pipeProgress.setBusinessPipeline(businessPipeline);
            pipeProgress.setContent(pipeProgress.getContent());
            pipeProgressRepository.save(pipeProgress);
        });
    }
}
