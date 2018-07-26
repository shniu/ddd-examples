package info.chaintech.july.dao;

import info.chaintech.july.domain.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shniu
 * @date 2018-07-26 下午8:46
 * @email niushaohan@digcredit.com
 */

public interface MailLogRepository extends JpaRepository<MailLog, Long> {
}
