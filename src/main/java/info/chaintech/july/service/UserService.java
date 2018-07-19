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
     */
    User login(LoginVo loginVo);
}
