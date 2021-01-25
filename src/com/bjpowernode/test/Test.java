package com.bjpowernode.test;

import com.bjpowernode.dao.EmpDao;
import com.bjpowernode.entity.Emp;

import java.util.List;
import java.util.Scanner;

/**
 * 江浩
 * 2020/11/9
 */
public class Test {
    public static void main(String[] args)throws Exception {

        Scanner sc = new Scanner(System.in);
        int flag = 0;
        Emp emp = null;
        List<Emp> deptList = null;
        System.out.println("---1.部门注册服务---");
        System.out.println("---2.部门查询服务---");
        System.out.println("---3.部门注销服务---");
        System.out.println("---4.部门更改服务---");
        System.out.println("请选择服务");
        flag = sc.nextInt();

        if (flag == 1) {

            //1.读取用户提供新部门信息
            System.out.println("请输入新职员编号");
            String deptNo = sc.next();
            System.out.println("请输入新职员姓名");
            String ename = sc.next();
            System.out.println("请输入新职员的领导编号");
            String mgr =sc.next();
            System.out.println("请输入新职员的入职时间");
            String data = sc.next();
            System.out.println("请输入新职员的工作");
            String job = sc.next();
            System.out.println("请输入新职员编号");
            String empno =sc.next();
            System.out.println("请输入新职员工资");
            Integer sal = sc.nextInt();
            System.out.println("请输入新职员补助");
            Integer comm = sc.nextInt();
            System.out.println("请输入新职员的部门编号");
            String deptno = sc.next();
            //2.帮助用户将新部门添加到远程部门表
            emp = new Emp(empno, ename, job, mgr, data, sal, comm, deptno);
            EmpDao.insert(emp);
            //3.将处理结果进行润色返回给用户
            if (flag == 0) {
                System.out.println("部门注册失败");
            } else {
                System.out.println("部门注册成功");
            }


        } else if (flag == 2) {
            deptList = EmpDao.findAll(emp);
            for (Emp emp1 : deptList) {
                System.out.println("部门编号 " + emp1.getDeptno() + " 职员姓名 " + emp1.getEname() + " 职员编号 " + emp1.getEmpno() + " 职员入职时间 " + emp1.getHireDate() + " 职员工资 " + emp1.getSal() + " 职员补助 " + emp1.getComm() + " 职员工作 " + emp1.getJob() + " 职员领导编号 " + emp1.getMgr());
            }
        } else if (flag == 3) {
            //1.获得用户需要删除部门编号
            System.out.println("请输入要删除部门编号");
            String deptNo = sc.next();
            //2.将部门从远程服务器删除
            flag = EmpDao.delete(emp,deptNo);
            //3.将业务处理结果返回
            if (flag == 0) {
                System.out.println("部门注销失败");
            } else {
                System.out.println("部门注销成功");
            }
        } else {
            //1.读取用户提供新部门信息
            System.out.println("请输入需要更新部门编号");
            String deptNo = sc.next();
            System.out.println("请输入新职员姓名");
            String ename = sc.next();
            System.out.println("请输入新职员的领导编号");
            String mgr =sc.next();
            System.out.println("请输入新职员的入职时间");
            String data = sc.next();
            System.out.println("请输入新职员的工作");
            String job = sc.next();
            System.out.println("请输入新职员编号");
            String empno =sc.next();
            System.out.println("请输入新职员工资");
            Integer sal = sc.nextInt();
            System.out.println("请输入新职员补助");
            Integer comm = sc.nextInt();
            System.out.println("请输入新职员的部门编号");
            String deptno = sc.next();

            emp = new Emp(empno, ename, job, mgr, data, sal, comm, deptNo);
            flag = EmpDao.update(emp,deptNo);

            if (flag == 0) {
                System.out.println("部门更新失败");
            } else {
                System.out.println("部门更新成功");
            }
        }
    }
}
