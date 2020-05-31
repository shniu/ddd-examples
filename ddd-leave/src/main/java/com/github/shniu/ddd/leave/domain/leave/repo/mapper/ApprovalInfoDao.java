package com.github.shniu.ddd.leave.domain.leave.repo.mapper;

import com.github.shniu.ddd.leave.domain.leave.repo.po.ApprovalInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author niushaohan
 * @date 2020/5/31 23
 */
public interface ApprovalInfoDao extends JpaRepository<ApprovalInfoPO, String> {
}
