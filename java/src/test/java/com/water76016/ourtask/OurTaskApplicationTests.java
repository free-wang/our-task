package com.water76016.ourtask;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest(classes = OurTaskApplication.class)
class OurTaskApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void getEncode(){
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        String mm_pub = "123456";
        String mm_encode = bcp.encode(mm_pub);
        if(bcp.matches(mm_pub,mm_encode)){
            System.out.println(mm_encode);
            System.out.println("密码校验成功");
        }else{
            System.out.println("密码错误");
        }
    }

}
