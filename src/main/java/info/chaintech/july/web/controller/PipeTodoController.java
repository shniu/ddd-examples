package info.chaintech.july.web.controller;

import info.chaintech.july.web.message.ResponseMessage;
import info.chaintech.july.web.vo.TodoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author shniu
 * @date 2018-07-10 下午4:33
 * @email niushaohan@digcredit.com
 */

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PipeTodoController {

    @ApiOperation(value = "添加某条商务线的todo", notes = "添加商务线的todo事项")
    @PostMapping("/todo")
    public ResponseMessage addTodo(@RequestBody TodoVo todoVo) {
        log.info("todoVo: {}", todoVo);
        return ResponseMessage.ok();
    }

    @ApiOperation(value ="获取todo待办列表", notes = "获取某条商务线下的所有todo待办列表")
    @GetMapping("/{bizId}/todo")
    public ResponseMessage getTodoCollections(@PathVariable int bizId) {
        log.info("bizId: {}", bizId);
        return ResponseMessage.ok();
    }

    @ApiOperation(value = "获取指定的某个todo", notes = "获取指定的某个todo")
    @GetMapping("/todo/{todoId}")
    public ResponseMessage getTodo(@PathVariable int todoId) {
        log.info("todoId: {}", todoId);
        return ResponseMessage.ok();
    }


}
