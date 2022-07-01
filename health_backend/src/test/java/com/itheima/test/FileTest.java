package com.itheima.test;

import org.junit.Test;

public class FileTest {

    @Test
    public void test(){
        String name="12231.jpg";
        String substring = name.substring(name.lastIndexOf("."));
        System.out.println(substring);
    }
}
