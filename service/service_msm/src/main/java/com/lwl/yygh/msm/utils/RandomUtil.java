package com.lwl.yygh.msm.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    /**
     * 生成四位验证码
     * @return 验证码
     */
    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    /**
     * 生成六位验证码
     * @return 验证码
     */
    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }

    /**
     * 给定数组，抽取n个数据
     * @param list 数组
     * @param n 数据个数
     * @return 随机数
     */
    public static List<Integer> getRandom(List<Integer> list, int n) {

        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("random初始化失败");
        }

        HashMap<Object, Object> hashMap = new HashMap<>();

        // 生成随机数字并存入HashMap
        for (int i = 0; i < list.size(); i++) {
            int number = 0;
            if (random == null) {
                System.out.println("random初始化失败");
            } else {
                number = random.nextInt(100) + 1;
            }
            hashMap.put(number, i);
        }

        // 从HashMap导入数组
        Object[] robjs = hashMap.values().toArray();

        List<Integer> r = new ArrayList<>();

        // 遍历数组并打印数据
        for (int i = 0; i < n; i++) {
            r.add(list.get((int) robjs[i]));
            System.out.print(list.get((int) robjs[i]) + "\t");
        }
        System.out.print("\n");
        return r;
    }

    private RandomUtil() {
    }
}
