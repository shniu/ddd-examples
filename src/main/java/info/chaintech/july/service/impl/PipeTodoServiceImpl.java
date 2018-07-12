package info.chaintech.july.service.impl;

import info.chaintech.july.dao.BusinessPipelineRepository;
import info.chaintech.july.dao.PipeTodoRepository;
import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.PipeTodo;
import info.chaintech.july.domain.enums.TodoStatus;
import info.chaintech.july.service.PipeTodoService;
import info.chaintech.july.service.dto.PipeTodoDto;
import info.chaintech.july.web.vo.TodoVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.util.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class PipeTodoServiceImpl implements PipeTodoService {
    private PipeTodoRepository pipeTodoRepository;
    private BusinessPipelineRepository businessPipelineRepository;

    @Override
    public PipeTodo addPipeTodo(TodoVo todoVo) {
        Optional<BusinessPipeline> bizLine = businessPipelineRepository.findById(todoVo.getBizId());
        return bizLine.map(businessPipeline -> {
            PipeTodo pipeTodo = new PipeTodo();
            pipeTodo.setBusinessPipeline(businessPipeline);
            pipeTodo.setContent(todoVo.getContent());
            pipeTodoRepository.save(pipeTodo);
            return pipeTodo;
        }).orElse(null);
    }

    @Override
    public List<PipeTodoDto> findAllTodos(long bizId) {
        Optional<BusinessPipeline> bizLine = businessPipelineRepository.findById(bizId);
        return bizLine.map(businessPipeline -> {
            List<PipeTodo> pipeTodos = pipeTodoRepository.findAllByBusinessPipelineAndTodoStatusNotIn(businessPipeline,
                    new ArrayList<TodoStatus>() {{
                        add(TodoStatus.Deleted);
                    }});
            return pipeTodos.stream().map(pipeTodo -> {
                PipeTodoDto pipeTodoDto = new PipeTodoDto();
                pipeTodoDto.setTid(pipeTodo.getTid());
                pipeTodoDto.setContent(pipeTodo.getContent());
                pipeTodoDto.setTodoStatus(pipeTodo.getTodoStatus().name());
                pipeTodoDto.setStatusDesc(pipeTodo.getTodoStatus().name());
                pipeTodoDto.setCreatedOn(DateUtils.format(pipeTodo.getCreatedOn(), "yyyy-MM-dd HH:mm:ss"));

                return pipeTodoDto;
            }).collect(Collectors.toList());
        }).orElse(null);
    }
}
