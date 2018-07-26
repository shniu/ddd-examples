package info.chaintech.july.dao;

import info.chaintech.july.domain.Recipients;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shniu
 * @date 2018-07-26 下午3:42
 */

public interface RecipientsRepository extends JpaRepository<Recipients, Long> {
}
