package info.chaintech.july.dao;

import info.chaintech.july.domain.BusinessPipeline;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shniu
 * @date 2018-07-11 下午8:21
 * @email niushaohan@digcredit.com
 */

public interface BusinessPipelineRepository extends JpaRepository<BusinessPipeline, Long> {
}
