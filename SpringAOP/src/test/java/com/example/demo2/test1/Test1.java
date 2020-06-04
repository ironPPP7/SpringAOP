package com.example.demo2.test1;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @Author wmy
 * @Date 2020/6/4 9:48
 * @Version 1.0
 */
public class Test1 {

    @Test
    public void test1() {
        String userId = "";
        Assert.notNull(userId, "用户不存在.");
    }

    @Test
    public void test2() {
        // 另一种写法
        String userId = "";
        if (userId == null) {
            throw new IllegalArgumentException("用户不存在.");
        }
    }
}
