package com.bjpowernode.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 江浩
 * 2020/11/9
 */
public class JdbcUtil {
    private static PreparedStatement ps;
    private static Connection con;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
     * 3.建立交通工具
     * con.preparedStatement()
     */
    public static Connection getcon()throws Exception{
        con = DriverManager.getConnection("msql:jdbc://localhost:3306/test","root","123");
        return con;
    }

    public static PreparedStatement getPs(String sql)throws Exception{
        ps = getcon().prepareStatement(sql);
        return ps;
    }
    public static int executeUpdate(String sql)throws Exception{
        int flag =0;
        ps = getPs(sql);
        flag=ps.executeUpdate();
        return flag;
    }
    public static ResultSet executeQu(String sql)throws Exception{
        ResultSet table = null;
        ps = getPs(sql);
        table=ps.executeQuery();
        return table;
    }

    /*
     * 5.资源销毁
     *
     */
    public static void close()throws Exception{
        if (ps != null){
            ps.close();
        }
        if (con != null){
            con.close();
        }
    }
    public static void close(ResultSet table)throws Exception{
        if (table != null){
            table.close();
        }
        close();
    }
}
