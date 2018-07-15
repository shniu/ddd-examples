package info.chaintech.july.service;

import info.chaintech.july.domain.PipeTodo;
import info.chaintech.july.service.dto.PipeTodoDto;
import info.chaintech.july.web.controller.ToggleTodoVo;
import info.chaintech.july.web.vo.TodoVo;

import java.util.List;

public interface PipeTodoService {

    /**
     * 增加todo
     */
    PipeTodo addPipeTodo(TodoVo todoVo);

    List<PipeTodoDto> findAllTodos(long bizId);

    /**
     * 删除todo
     */
    boolean deleteTodo(long todoId);

    boolean toggleTodo(ToggleTodoVo toggleTodoVo);
}
