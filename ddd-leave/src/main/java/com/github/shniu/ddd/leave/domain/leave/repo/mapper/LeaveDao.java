package com.github.shniu.ddd.leave.domain.leave.repo.mapper;

import com.github.shniu.ddd.leave.domain.leave.repo.po.LeavePO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
public interface LeaveDao extends JpaRepository<LeavePO, String> {
}
