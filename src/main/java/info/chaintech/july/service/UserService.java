package info.chaintech.july.service;

import info.chaintech.july.domain.User;
import info.chaintech.july.web.vo.LoginVo;

/**
 * @author shniu
 * @date 2018-06-08 下午7:38
 * @email niushaohan@digcredit.com
 */

public interface UserService {

    /**
     * 登录
     *
     * @param loginVo login body
     * @return 登录用户对象, 不存在返回 null
     */
    User login(LoginVo loginVo);
}
