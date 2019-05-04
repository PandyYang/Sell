package com.pandy.utils;

import java.util.Random;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 21:28
 * @Version 1.0
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式 时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer a = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(a);
    }

}
