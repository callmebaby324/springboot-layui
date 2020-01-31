package com.haiyu;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class CommonTest {

    @Test
    public void test1(){
        String s1 = DigestUtils.md5DigestAsHex("baby138725".getBytes());
        System.out.println("----:"+s1);
    }

}
