package info.chaintech.july.dao;

import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.PipeTodo;
import info.chaintech.july.domain.enums.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PipeTodoRepository extends JpaRepository<PipeTodo, Long> {

    List<PipeTodo> findAllByBusinessPipelineAndTodoStatusNotIn(
            BusinessPipeline businessPipeline, List<TodoStatus> todoStatuses);
}
