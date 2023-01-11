package com.company.util;

import java.sql.*;
import java.util.LinkedList;

public class StuInf {
    private String StuId;
    private String StuName;
    private boolean Sex;
    private int Age;
    private boolean isnew = true;//用于判断实例对象是否在数据库中是新的记录
    static Connection conn;
    static {
        /*与Mysql数据库建立连接*/
        try {
            conn = DriverManager.getConnection(ConfigManager.getProp("url"), ConfigManager.getProp("user"), ConfigManager.getProp("password"));
        } catch (SQLException e) {
            /*异常处理*/
            System.out.println("数据库连接失败！");
        }
    }
    /*构造方法*/
    public StuInf() {

    }

    public StuInf(String stuId, String stuName, boolean sex, int age) {
        StuId = stuId;
        StuName = stuName;
        Sex = sex;
        Age = age;
    }

    /*set和get方法*/
    public void setStuId(String stuId) {
        StuId = stuId;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public void setSex(boolean sex) {
        Sex = sex;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getStuId() {
        return StuId;
    }

    public String getStuName() {
        return StuName;
    }

    public boolean isSex() {
        return Sex;
    }

    public int getAge() {
        return Age;
    }

    /*将实体对象映射到数据库中*/
    public boolean save() throws SQLException {
        /*如果要保存的实例对象在数据库中不存在，则执行插入语句*/
        if(this.isnew) {
            /*在StuInf表中插入新记录*/
            String sql = "insert into stuinf(StuId, StuName, Sex, Age) values(?, ?, ?, ?)";
            PreparedStatement pst = null;//预处理sql语句
            pst = conn.prepareStatement(sql);
            /*将参数值传递到pst中*/
            pst.setString(1, StuId);
            pst.setString(2, StuName);
            pst.setBoolean(3, Sex);
            pst.setInt(4, Age);
            pst.executeUpdate();//执行sql语句
            pst.close();
        }
        /*如果要保存的实例对象在数据库中存在，则执行修改语句（不能修改主键字段内容）*/
        else {
            /*在StuInf表中修改原有的记录*/
            String sql = "update StuInf set StuName= ?, Sex=?, Age=? where StuId= ?";
            PreparedStatement pst = conn.prepareStatement(sql);//预处理sql语句
            /*将参数值传递到pst中*/
            pst.setString(1, this.getStuName());
            pst.setBoolean(2, this.isSex());
            pst.setInt(3, this.getAge());
            pst.setString(4, this.getStuId());
            pst.executeUpdate();//执行sql语句
            pst.close();
        }
        System.out.println("保存成功！");
        return true;
    }

    /*展示该实例对象的所有字段*/
    public boolean show(){
        System.out.print("StuId:"+this.getStuId()+"  ");
        System.out.print("StuName:"+this.getStuName()+"  ");
        System.out.print("Sex:"+this.isSex()+"  ");
        System.out.println("StuAge:"+this.getAge());
        return true;
    }
    /*删除该实例对象在数据库中的记录*/
    public boolean delete() throws SQLException {
        /*在StuInf表中删除原有的记录*/
        String sql = "delete from StuInf where StuId= ? ";
        PreparedStatement pst = conn.prepareStatement(sql);//预处理sql语句
        /*将参数值传递到pst中*/
        pst.setString(1, this.getStuId());
        pst.executeUpdate();//执行sql语句
        pst.close();
        System.out.println("删除成功！");
        return true;
    }
/*类方法*/
    /*按条件获取数据库中的结果对象集*/
    public static LinkedList<StuInf> filter(String condition) throws SQLException {
        Statement st = conn.createStatement();
        String sql = "select * from StuInf where "+condition;  //查询表StuInf的所有字段的数据
        ResultSet rs = st.executeQuery(sql); //执行sql语句，获取结果对象集
        LinkedList<StuInf> stuInfs = new LinkedList<StuInf>();
        while (rs.next()){
            StuInf stuinf = new StuInf();
            stuinf.setStuId(rs.getString("StuId"));
            stuinf.setStuName(rs.getString("StuName"));
            stuinf.setSex(rs.getBoolean("Sex"));
            stuinf.setAge(rs.getInt("Age"));
            stuinf.isnew = false;
            stuInfs.add(stuinf);
        }
        return stuInfs;
    }
}
