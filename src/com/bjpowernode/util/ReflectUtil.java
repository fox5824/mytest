package com.bjpowernode.util;

import java.lang.reflect.Field;

/**
 * 江浩
 * 2020/11/9
 */
public class ReflectUtil {
    //生成添加字段名
    //insert into 表名 (字段1,字段2,。。。。) values(值1,值2)
    public static String createInsert(Object instance) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer("insert into ");
        StringBuffer columns = new StringBuffer("(");
        StringBuffer values = new StringBuffer(" values(");
        //得到需要操作的表文件名【实体类的类名应该与表文件名相同】
        Class classFile = instance.getClass();
        String tableName = classFile.getSimpleName();
        //得到需要复制的字段名
        Field[] fieldArray = classFile.getDeclaredFields();
        for (Field field : fieldArray) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object value = field.get(instance);
            if (value != null){
                if (!columns.toString().equals("(")){
                    columns.append(",");
                    values.append(",");
                }
                columns.append(fieldName);
                values.append("'");
                values.append(value);
                values.append("'");
            }
        }
        columns.append(")");
        values.append(")");
        sql.append(tableName);
        sql.append(columns);
        sql.append(values);
        System.out.println(sql.toString());
        return sql.toString();
    }

    //生成删除字段名的代码
    //delete from 表 where 主键字段 = 值

    public static String createDelete(Object instance,String primaryKey) throws IllegalAccessException, NoSuchFieldException {
        StringBuffer sql = new StringBuffer("delete from ");
        Field field = null;
        Object value =null;
        Class classFile = instance.getClass();
        String tableName = classFile.getSimpleName();
        field = classFile.getDeclaredField(primaryKey);
        field.setAccessible(true);
        value = field.get(instance);
        //链接字符串
        sql.append(tableName);
        sql.append(" where ");
        sql.append(" = ");
        sql.append("'");
        sql.append(value);
        sql.append("'");
        System.out.println(sql);
        return sql.toString();
    }

    /*
    *
    * update 表 set 字段名 = '值',字段名 = '值' where 主键字段名 = '值'
    *
    */

    public static String createUpdate(Object instance,String primaryKey) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer("update ");
        StringBuffer set = new StringBuffer(" set ");
        StringBuffer where = new StringBuffer(" where ");
        Class<?> classFile = instance.getClass();
        String tableName = classFile.getSimpleName();
        Field[] fieldArray = classFile.getDeclaredFields();
        for (Field f : fieldArray) {
            f.setAccessible(true);
            String fieldName = f.getName();
            Object value = f.get(instance);
            if (value != null && !fieldName.equals(primaryKey)) {
                if (!" set ".equals(set.toString())) {
                    set.append(",");
                }
                set.append(fieldName);
                set.append("=");
                set.append("'");
                set.append(value);
                set.append("'");
            } else if (fieldName.equals(primaryKey)) {
                where.append(fieldName);
                where.append("=");
                where.append("'");
                where.append(value);
                where.append("'");
            }
        }
        //组装
        sql.append(tableName);
        sql.append(set);
        sql.append(where);
        System.out.println(sql);
        return sql.toString();
    }

    //查
    // select * from 表
    public static String createSelect(Object instance){
        StringBuffer sql = new StringBuffer("select * from ");
        Class<?> classFile = instance.getClass();
        String tableName = classFile.getSimpleName();
        sql.append(tableName);
        System.out.println(sql);
        return sql.toString();
    }
}
