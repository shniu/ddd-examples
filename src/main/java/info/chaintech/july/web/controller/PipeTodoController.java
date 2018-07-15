package info.chaintech.july.web.controller;

import info.chaintech.july.conf.aop.CoolLogger;
import info.chaintech.july.domain.PipeTodo;
import info.chaintech.july.service.PipeTodoService;
import info.chaintech.july.service.dto.PipeTodoDto;
import info.chaintech.july.web.message.MapResponseMessage;
import info.chaintech.july.web.message.ResponseMessage;
import info.chaintech.july.web.vo.TodoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shniu
 * @date 2018-07-10 下午4:33
 * @email niushaohan@digcredit.com
 */

@Api(value = "商务线待办事项接口", description = "商务线待办事项接口集合", consumes = "application/json", produces = "application/json")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class PipeTodoController {

    private PipeTodoService pipeTodoService;

    @ApiOperation(value = "添加某条商务线的todo", notes = "添加商务线的todo事项")
    @CoolLogger(remark = "添加某条商务线的todo", action = "addTodo")
    @PostMapping("/todo")
    public MapResponseMessage addTodo(@RequestBody TodoVo todoVo) {
        PipeTodo pipeTodo = pipeTodoService.addPipeTodo(todoVo);
        return MapResponseMessage.ok()
                .put("tid", pipeTodo.getTid());
    }

    @ApiOperation(value = "获取todo待办列表", notes = "获取某条商务线下的所有todo待办列表")
    @CoolLogger(remark = "获取todo待办列表", action = "getTodos", targetType = "todo")
    @GetMapping("/{bizId}/todo")
    public ResponseMessage<List<PipeTodoDto>> getTodoCollections(@PathVariable long bizId) {
        List<PipeTodoDto> pipeTodoDtos = pipeTodoService.findAllTodos(bizId);
        return ResponseMessage.ok(pipeTodoDtos);
    }

    @ApiOperation(value = "获取指定的某个todo", notes = "获取指定的某个todo")
    @CoolLogger(remark = "获取指定的某个todo")
    @GetMapping("/todo/{todoId}")
    public ResponseMessage getTodo(@PathVariable int todoId) {
        log.info("todoId: {}", todoId);
        return ResponseMessage.ok();
    }

    /**
     * 删除todo
     */
    @ApiOperation(value = "删除指定的某个todo", notes = "删除指定的某个todo")
    @CoolLogger(remark = "删除指定的某个todo", action = "deleteTodo")
    @DeleteMapping("/todo/{todoId}")
    public ResponseMessage<Void> delTodo(@PathVariable int todoId) {
        pipeTodoService.deleteTodo(todoId);
        return ResponseMessage.ok();
    }

    /**
     * 修改todo
     */
    @ApiOperation(value = "修改todo", notes = "修改todo")
    @CoolLogger(remark = "修改todo", action = "toggleTodo")
    @PutMapping("/todo")
    public ResponseMessage<Void> delTodo(@RequestBody ToggleTodoVo toggleTodoVo) {
        pipeTodoService.toggleTodo(toggleTodoVo);
        return ResponseMessage.ok();
    }
}
