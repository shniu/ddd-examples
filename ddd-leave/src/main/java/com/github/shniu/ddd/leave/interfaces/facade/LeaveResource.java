package com.github.shniu.ddd.leave.interfaces.facade;

import com.github.shniu.ddd.leave.application.service.LeaveApplicationService;
import com.github.shniu.ddd.leave.infra.common.api.Response;
import com.github.shniu.ddd.leave.interfaces.assembler.LeaveAssembler;
import com.github.shniu.ddd.leave.interfaces.dto.LeaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class LeaveResource {
    private LeaveApplicationService leaveApplicationService;

    public LeaveResource(LeaveApplicationService leaveApplicationService) {
        this.leaveApplicationService = leaveApplicationService;
    }

    @PostMapping(value = "/leave")
    public Response createLeaveInfo(@RequestBody final LeaveDTO leaveDTO) {
        leaveApplicationService.createLeaveInfo(LeaveAssembler.toDO(leaveDTO));
        return Response.ok();
    }

    @PutMapping(value = "/leave")
    public Response updateLeaveInfo(@RequestBody final LeaveDTO leaveDTO) {
        return Response.ok();
    }

    @GetMapping(value = "/leave/{leaveId}")
    public Response queryLeaveById(@PathVariable final String leaveId) {
        return Response.ok();
    }

    @PostMapping(value = "/leave/{leaveId}/approval")
    public Response createApproval(@PathVariable final String leaveId, @RequestBody LeaveDTO leaveDTO) {
        return Response.ok();
    }

    /**
     * 根据申请人查询所有的请假单.
     *
     * @param applicantId 申请人id
     * @return 请假单列表
     */
    @GetMapping(value = "/applicant/{applicantId}/leave")
    public Response queryLeaveByApplicant(@PathVariable final String applicantId) {
        return Response.ok();
    }

    /**
     * 查询待办任务，根据审批人id查询请假单
     *
     * @param approverId 审核人id
     * @return 请假单列表
     */
    @GetMapping(value = "/approver/{approverId}/leave")
    public Response queryLeaveByApprover(@PathVariable final String approverId) {
        return Response.ok();
    }
}
