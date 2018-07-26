package info.chaintech.july.dao;

import info.chaintech.july.domain.PipeProgress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shniu
 * @date 2018-07-26 下午4:16
 * @email niushaohan@digcredit.com
 */

public interface PipeProgressRepository extends JpaRepository<PipeProgress, Long> {
}
