package com.bjpowernode.dao;

import com.bjpowernode.entity.Emp;
import com.bjpowernode.util.JdbcUtil;
import com.bjpowernode.util.ReflectUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 江浩
 * 2020/11/9
 */
public class EmpDao {
    //对Emp表进行insert操作流程
    public static int insert(Emp emp) throws Exception {
        String sql = null;
        int counts = 0;
        sql = ReflectUtil.createInsert(emp);
        try {
            counts = JdbcUtil.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close();
        }
        return counts;
    }

    //对EMp表进行delete操作流程
    public static int delete(Emp emp,String deptno) throws Exception {
        String sql = null;
        int counts = 0;
        sql = ReflectUtil.createDelete(emp, deptno);
        try {
            counts = JdbcUtil.executeUpdate(sql);
        } finally {
            JdbcUtil.close();
        }
        return counts;
    }

    //对emp表进行查询操作流程
    public static List findAll(Emp emp) throws Exception {
        ResultSet table = null;
        String sql = null;
        List list = null;
        sql = ReflectUtil.createSelect(emp);
        table = JdbcUtil.executeQu(sql);
        while (table.next()) {
            list.add(table.getString(1));
            list.add(table.getString(2));
            list.add(table.getString(3));
            list.add(table.getString(4));
            list.add(table.getString(5));
            list.add(table.getInt(6));
            list.add(table.getInt(7));
            list.add(table.getString(8));
        }
        JdbcUtil.close(table);
        return list;
    }

    //对emp表进行修改操作
    public static int update(Emp emp,String deptno) throws Exception {
        String sql = null;
        int counts = 0;
        sql = ReflectUtil.createUpdate(emp, deptno);
        try {
            counts = JdbcUtil.executeUpdate(sql);
        } finally {
            JdbcUtil.close();
        }
        return counts;
    }

}
