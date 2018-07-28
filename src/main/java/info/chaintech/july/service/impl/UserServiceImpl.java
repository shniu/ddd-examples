package info.chaintech.july.service.impl;

import info.chaintech.july.dao.UserRepository;
import info.chaintech.july.domain.User;
import info.chaintech.july.service.UserService;
import info.chaintech.july.service.dto.InChargeUserDto;
import info.chaintech.july.web.vo.LoginVo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shniu
 */
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User login(LoginVo loginVo) {
        Optional<User> optionalUser = userRepository.findByUsername(loginVo.getUsername());
        return optionalUser.orElse(null);
    }

    @Override
    public List<InChargeUserDto> findAllInChargeUser() {
        return userRepository.findAll().stream().map(user -> {
            InChargeUserDto inChargeUserDto = new InChargeUserDto();
            inChargeUserDto.setUid(user.getUid());
            inChargeUserDto.setName(user.getRealName());
            return inChargeUserDto;
        }).collect(Collectors.toList());
    }
}
