package com.bjpowernode.util;

import com.bjpowernode.entity.Emp;

/**
 * 江浩
 * 2020/11/9
 */
public class TestUtil {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Emp emp =new Emp("4567", "zhangsan", "hr", "7894", "2020-08-06", 4000, 400, "7894");
        ReflectUtil.createInsert(emp);
        ReflectUtil.createDelete(emp, "deptno");
        ReflectUtil.createUpdate(emp, "deptno");
        ReflectUtil.createSelect(emp);
    }
}
