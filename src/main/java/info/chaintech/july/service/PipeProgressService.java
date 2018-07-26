package info.chaintech.july.service;

import info.chaintech.july.service.dto.ProgressQueryDto;
import info.chaintech.july.web.vo.NewProgressVo;

import java.util.List;

/**
 * @author shniu
 * @date 2018-07-26 下午4:06
 * @email niushaohan@digcredit.com
 */

public interface PipeProgressService {

    /**
     * 查询进展
     *
     * @param bizId bizId
     * @return 进展列表
     */
    List<ProgressQueryDto> queryProgressByBizId(long bizId);

    /**
     * 添加进展
     * @param newProgressDto 新进展
     */
    void addProgress(NewProgressVo newProgressDto);
}
