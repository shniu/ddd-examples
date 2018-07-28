package info.chaintech.july.web.controller;

import info.chaintech.july.service.UserService;
import info.chaintech.july.service.dto.InChargeUserDto;
import info.chaintech.july.web.message.ResponseMessage;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shniu
 * @date 2018-07-28 下午3:54
 * @email niushaohan@digcredit.com
 */

@Api(value = "用户中心接口", description = "用户中心的接口集合", consumes = "application/json", produces = "application/json")
@RestController
@RequestMapping("/api/v1/user")
@Validated
@Slf4j
public class AccountController {

    private UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/inCharge/list")
    public ResponseMessage<List<InChargeUserDto>> inChargeUserList() {
        List<InChargeUserDto> inChargeUserDtos = userService.findAllInChargeUser();
        return ResponseMessage.ok(inChargeUserDtos);
    }
}
