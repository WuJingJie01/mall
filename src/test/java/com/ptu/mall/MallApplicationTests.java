package com.ptu.mall;

import com.ptu.mall.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testGenPasswordMD5() {
        System.out.println(MD5Util.MD5Encode("123456", "UTF-8"));
    }

}
