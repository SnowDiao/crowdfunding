package org.snow.crowd.test;


import org.junit.Test;
import org.snow.crowd.util.CrowdUtil;

public class TestMd5 {
    @Test
    public void test(){
        String source = "123";
        String result = CrowdUtil.md5(source);
        System.out.println(result);

    }
}
