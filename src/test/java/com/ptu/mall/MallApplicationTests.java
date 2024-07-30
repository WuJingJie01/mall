package com.ptu.mall;

import com.ptu.mall.mapper.CategoryMapper;
import com.ptu.mall.utils.MD5Util;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class MallApplicationTests {
    private final CategoryMapper categoryMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void testGenPasswordMD5() {
        System.out.println(MD5Util.MD5Encode("123456", "UTF-8"));
    }


}
