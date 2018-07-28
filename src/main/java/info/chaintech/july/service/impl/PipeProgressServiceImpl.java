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
import org.apache.tools.ant.util.DateUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<BusinessPipeline> businessPipelineOptional = businessPipelineRepository.findById(bizId);

        return businessPipelineOptional.map(businessPipeline -> businessPipeline.getPipeProgresses()
                .stream()
                .sorted(Comparator.comparing(PipeProgress::getCreatedOn).reversed())
                .map(pipeProgress -> {
                    ProgressQueryDto progressQueryDto = new ProgressQueryDto();
                    progressQueryDto.setContent(pipeProgress.getContent());
                    progressQueryDto.setCreatedOn(DateUtils.format(pipeProgress.getCreatedOn(), "yyyy-MM-dd HH:MM"));
                    return progressQueryDto;
                }).collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }

    @Override
    public void addProgress(NewProgressVo newProgressDto) {
        Optional<BusinessPipeline> businessPipelineOptional = businessPipelineRepository.findById(newProgressDto.getBizId());
        businessPipelineOptional.ifPresent(businessPipeline -> {

            PipeProgress pipeProgress = new PipeProgress();
            pipeProgress.setBusinessPipeline(businessPipeline);
            pipeProgress.setContent(newProgressDto.getContent());
            pipeProgress.setTrackUser(businessPipeline.getCreatedUser());
            pipeProgressRepository.save(pipeProgress);
        });
    }
}
