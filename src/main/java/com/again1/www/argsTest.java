package com.again1.www;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class argsTest {
    public static void showArgs(String... args) {
        int count = 0;
        for (String one : args) {
            count++;
            log.info("i is {} , one is {}", count, one);
        }
    }

    public static void main(String[] args) {
        argsTest.showArgs("s5", "s2", "s3", "s1");
    }

}
