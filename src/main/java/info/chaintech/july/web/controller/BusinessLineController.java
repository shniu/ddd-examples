package info.chaintech.july.web.controller;

import info.chaintech.july.conf.aop.CoolLogger;
import info.chaintech.july.service.BusinessLineService;
import info.chaintech.july.service.dto.BizPipelinesPageableDto;
import info.chaintech.july.web.message.MapResponseMessage;
import info.chaintech.july.web.message.ResponseMessage;
import info.chaintech.july.web.vo.NewBizLineVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author shniu
 * @date 2018/7/9
 */

@Api(value = "商务线接口", description = "商务线相关的接口集合", consumes = "application/json", produces = "application/json")
@RestController
@RequestMapping("/api/v1/pipeline")
@Validated
@Slf4j
public class BusinessLineController {
    private BusinessLineService businessLineService;

    public BusinessLineController(BusinessLineService businessLineService) {
        this.businessLineService = businessLineService;
    }

    @ApiOperation(value = "获取商务线列表", notes = "商务线的列表，可分页获取", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", paramType = "path", value = "页码", dataType = "string"),
            @ApiImplicitParam(name = "pageSize", paramType = "path", value = "每页大小", dataType = "string")
    })
    @CoolLogger(action = "getBizPipelines", remark = "获取商务线列表")
    @GetMapping("/list/{pageNo}/{pageSize}")
    public MapResponseMessage lineListPageable(@PathVariable int pageNo, @PathVariable int pageSize) {
        BizPipelinesPageableDto bizPipelinesPageableDto = businessLineService.queryBizLinesPageable(PageRequest.of(pageNo, pageSize));
        return MapResponseMessage.ok()
                .put("lines", bizPipelinesPageableDto.getBizLineDtoList())
                .put("totalElements", bizPipelinesPageableDto.getTotalElements())
                .put("pageNo", pageNo)
                .put("pageSize", pageSize);
    }

    @ApiOperation(value = "添加商务线", notes = "添加一条商务线，返回商务线id")
    @CoolLogger(action = "addBusinessLine", remark = "添加一条商务线", targetType = "pipeline")
    @PostMapping(value = "/info", produces = "application/json", consumes = "application/json")
    public ResponseMessage addBusinessLine(@Validated @RequestBody NewBizLineVo newBizLineVo) {
        businessLineService.addBizPipeline(newBizLineVo);
        return ResponseMessage.ok();
    }

    @ApiOperation(value = "删除商务线", notes = "删除一条商务线")
    @CoolLogger(remark = "删除一条商务线", action = "deleteBizLine")
    @DeleteMapping(value = "/info/{bizId}", produces = "application/json", consumes = "application/json")
    public ResponseMessage<Void> deleteBizLine(@PathVariable long bizId) {
        businessLineService.deleteBizLine(bizId);
        return ResponseMessage.ok();
    }
}
