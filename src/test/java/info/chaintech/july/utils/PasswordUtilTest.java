package info.chaintech.july.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class PasswordUtilTest {
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testPasswordGen() {
        String encode = passwordEncoder.encode("qlmlovensh");
        log.info(encode);
    }
}
