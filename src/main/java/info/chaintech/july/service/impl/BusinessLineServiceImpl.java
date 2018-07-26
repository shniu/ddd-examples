package info.chaintech.july.service.impl;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.PipeTodo;
import info.chaintech.july.domain.User;
import info.chaintech.july.domain.enums.PipelineStatus;
import info.chaintech.july.domain.enums.TodoStatus;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.dto.BizLineDto;
import info.chaintech.july.service.dto.BizPipelinesPageableDto;
import info.chaintech.july.service.dto.PendingMailDto;
import info.chaintech.july.web.security.SecurityHelper;
import info.chaintech.july.web.vo.NewBizLineVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author shniu
 * @date 2018/7/9
 */

@AllArgsConstructor
@Slf4j
public class BusinessLineServiceImpl implements BusinessLineService {

    private BusinessPipelineRepository businessPipelineRepository;

    @Override
    public BizPipelinesPageableDto queryBizLinesPageable(Pageable pageable) {
        Page<BusinessPipeline> all = businessPipelineRepository.findAllByDisabledFalse(pageable);
        BizPipelinesPageableDto bizPipelinesPageableDto = new BizPipelinesPageableDto();
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
    @Transactional(rollbackFor = Exception.class)
    public void addBizPipeline(NewBizLineVo newBizLineVo) {
        User loginUser = SecurityHelper.getUser();
        BusinessPipeline businessPipeline = new BusinessPipeline();
        businessPipeline.setTopic(newBizLineVo.getTopic());
        businessPipeline.setStatus(PipelineStatus.valueOf(newBizLineVo.getStatus()));
        businessPipeline.setInChargeUser(newBizLineVo.getInChargeUser());
        businessPipeline.setCreatedUser(loginUser);
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

    @Override
    public List<PendingMailDto> pendingMailBusinessLines() {

        log.info("整合待发送的邮件...");

        Page<BusinessPipeline> allPendingPipelines = businessPipelineRepository.findAllByDisabledFalse(
                PageRequest.of(0, Integer.MAX_VALUE)
        );

        List<PendingMailDto> pendingMailDtoList = new ArrayList<>();
        allPendingPipelines.getContent().stream()
                .collect(groupingBy(BusinessPipeline::getInChargeUser))
                .forEach((inChargeUser, businessPipelines) -> {
                    // Every Recipients
                    PendingMailDto pendingMailDto = new PendingMailDto();
                    pendingMailDto.setTo("syphniushaohan@126.com");  // Todo
                    pendingMailDto.setTitle(titleTemplate(inChargeUser));
                    businessPipelines.forEach(businessPipeline -> {
                        PendingMailDto.BizEmailDto bizEmailDto = pendingMailDto.new BizEmailDto();
                        bizEmailDto.setTopic(businessPipeline.getTopic());
                        bizEmailDto.setStatus(businessPipeline.getStatus().name());

                        List<String> todoList = businessPipeline.getPipeTodoList().stream()
                                .filter(pipeTodo -> TodoStatus.Todo.equals(pipeTodo.getTodoStatus()))
                                .map(PipeTodo::getContent)
                                .collect(Collectors.toList());
                        bizEmailDto.setTodoList(todoList);

                        pendingMailDto.getBizEmailDtoList().add(bizEmailDto);
                    });

                    pendingMailDtoList.add(pendingMailDto);
                });

        return pendingMailDtoList;
    }

    private String titleTemplate(String inChargeUserName) {
        String tpl = "%s 商务进展（%s）";
        return String.format(
                tpl,
                DateUtils.format(new Date(), "yyyy.MM.dd"),
                inChargeUserName
        );
    }
}
