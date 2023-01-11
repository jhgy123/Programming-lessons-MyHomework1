package com.company;

import com.company.util.ConfigManager;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        /*测试getProp()函数是否有效*/
        boolean test1 = true;
        if(test1){
            System.out.println("user:" + ConfigManager.getProp("user"));
            System.out.println("url:" + ConfigManager.getProp("url"));
            System.out.println("password:" + ConfigManager.getProp("password"));
            System.out.println("driver:" + ConfigManager.getProp("driver"));
        }

        /*使用JDBC完成读取、新增、修改、删除相应数据的操作*/
        boolean test2 = true;
        if(test2) {

                /*与Mysql数据库建立连接*/
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(ConfigManager.getProp("url"), ConfigManager.getProp("user"), ConfigManager.getProp("password"));
            } catch (SQLException e) {
                /*异常处理*/
                System.out.println("数据库连接失败！");
            }
            try {
                /*创建sql1语句，查询CrouseInf表的数据*/
                boolean test3 = true;
                if (test3) {
                    Statement st1 = conn.createStatement();
                    String sql1 = "select * from courseinf";  //查询表CourseInf的所有字段的数据
                    ResultSet rs = st1.executeQuery(sql1); //执行sql语句，获取结果对象集
                    /*遍历输出各条记录*/
                    while (rs.next()) {
                        System.out.print(" CourseCode:" + rs.getString("CourseCode"));
                        System.out.print(" CourseName:" + rs.getString("CourseName"));
                        System.out.println(" Hours:" + rs.getInt("Hours"));
                    }
                    rs.close();
                    st1.close();
                    System.out.println("查询成功！");
                }
            }catch (SQLException e) {
                /*异常处理*/
                System.out.println("查询操作失败！");
            }

            try {
                /*创建sql2语句，新增CrouseInf表记录*/
                boolean test4 = false;
                if (test4) {
                    /*在CourseInf表中插入新记录*/
                    String sql2 = "insert into courseinf(CourseCode, CourseName, Hours) values(?, ?, ?)";
                    PreparedStatement pst1 = conn.prepareStatement(sql2);//预处理sql2语句
                    /*将参数值传递到pst1中*/
                    pst1.setString(1, "1923301");
                    pst1.setString(2, "大学英语");
                    pst1.setInt(3, 26);
                    pst1.executeUpdate();//执行sql语句
                    System.out.println("插入成功！");
                    pst1.close();
                }
            }catch (SQLException e) {
                System.out.println("插入操作失败！");
            }

            try{
                /*创建sql3语句，修改CrouseInf表记录*/
                boolean test5 = false;
                if(test5) {
                    /*在CourseInf表中修改原有的记录*/
                    String sql3 = "update CourseInf set CourseName= ? where CourseCode= ?";
                    PreparedStatement pst2 = conn.prepareStatement(sql3);//预处理sql3语句
                    /*将参数值传递到pst2中*/
                    pst2.setString(1, "大学英语读写（3）");
                    pst2.setString(2, "1923301");
                    int state = pst2.executeUpdate();//执行sql语句
                    //System.out.println(state);
                    /*异常检测*/
                    if(state==0){
                        throw new SQLException("需要修改的对象不存在！\n修改操作失败！");
                    }
                    System.out.println("修改成功！");
                    pst2.close();
                }

                /*创建sql4语句，删除CrouseInf表中满足一定条件的记录*/
                boolean test6 = true;
                if(test6) {
                    /*在CourseInf表中删除原有的记录*/
                    String sql4 = "delete from courseinf where CourseCode= ? ";
                    PreparedStatement pst3 = conn.prepareStatement(sql4);//预处理sql4语句
                    /*将参数值传递到pst3中*/
                    pst3.setString(1, "1923301");
                    int state = pst3.executeUpdate();//执行sql语句
                    //System.out.println(state);
                    /*异常检测*/
                    if(state==0){
                        throw new SQLException("需要删除的对象不存在！\n删除操作失败！");
                    }
                    System.out.println("删除成功！");
                    pst3.close();
                }

            } catch (SQLException e) {
                /*异常处理*/
                System.out.println(e.getMessage());
//                e.printStackTrace();
            }
        }
    }
}
