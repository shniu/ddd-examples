package info.chaintech.july.dao;

import info.chaintech.july.domain.BusinessPipeline;
import info.chaintech.july.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shniu
 * @date 2018-07-11 下午8:21
 * @email niushaohan@digcredit.com
 */

public interface BusinessPipelineRepository extends JpaRepository<BusinessPipeline, Long> {

    /**
     * 查询可用商务线
     *
     * @param pageable page info
     * @return Page of BusinessPipeline
     */
    Page<BusinessPipeline> findAllByDisabledFalse(Pageable pageable);

    /**
     * 按用户查询所有商务线
     *
     * @param createdUser 创建用户
     * @param pageable    分页
     * @return Page of BusinessPipeline
     */
    Page<BusinessPipeline> findAllByCreatedUserAndDisabledFalse(User createdUser, Pageable pageable);
}
