package com.water76016.ourtask;

import com.water76016.ourtask.common.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = OurTaskApplication.class)
class OurTaskApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void commonTest(){
        Long userId = 123L;
        JwtUtils jwtUtils = new JwtUtils();
        String one = jwtUtils.generateToken(userId);
        System.out.println(jwtUtils.getSecret());
        System.out.println(jwtUtils.getExpire());
        System.out.println(one);
    }

}
