package info.chaintech.july.web.controller;


import info.chaintech.july.conf.aop.CoolLogger;
import info.chaintech.july.service.PipeProgressService;
import info.chaintech.july.service.dto.ProgressQueryDto;
import info.chaintech.july.web.message.ResponseMessage;
import info.chaintech.july.web.vo.NewProgressVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进展情况
 *
 * @author shniu
 */

@Api(value = "商务线进展情况接口", description = "商务线进展情况接口集合", consumes = "application/json", produces = "application/json")
@RestController
@RequestMapping(value = "/api/v1")
public class PipeProgressController {

    private PipeProgressService pipeProgressService;

    public PipeProgressController(PipeProgressService pipeProgressService) {
        this.pipeProgressService = pipeProgressService;
    }

    @ApiOperation(value = "获取某个商务线下的进展情况", notes = "获取某个商务线下的进展情况", produces = "application/json")
    @GetMapping(value = "/progress/{bizId}")
    @CoolLogger(remark = "获取某个商务线下的进展情况", action = "getProgress", targetType = "pipeProgress")
    public ResponseMessage<List<ProgressQueryDto>> getProgress(@PathVariable long bizId) {
        List<ProgressQueryDto> progressQueryDtos = pipeProgressService.queryProgressByBizId(bizId);
        return ResponseMessage.ok(progressQueryDtos);
    }

    @ApiOperation(value = "添加某个商务线的进展情况", notes = "添加某个商务线的进展情况", produces = "application/json")
    @PostMapping(value = "/progress")
    @CoolLogger(remark = "添加某个商务线的进展情况", action = "addProgress", targetType = "pipeProgress")
    public ResponseMessage<Void> addProgress(@RequestBody NewProgressVo newProgressDto) {
        pipeProgressService.addProgress(newProgressDto);
        return ResponseMessage.ok();
    }

}
