package com.company.util;

import java.sql.SQLException;
import java.util.LinkedList;

public class Test {
    public static void main(String[] arg) throws SQLException {
        
        StuInf a = new StuInf();
        StuInf b = new StuInf("20201001","Jack",true,20);
        a.setAge(30);
        a.setStuName("Tom");
        a.setStuId("20201002");
        a.setSex(false);
        a.save();
        b.save();

//        LinkedList<StuInf> stuInfs=StuInf.filter("StuName='Jack'");
//        StuInf c=stuInfs.getFirst();
//        c.show();

//        LinkedList<StuInf> stuInfs1=StuInf.filter("StuId='20201002'");
//        StuInf d=stuInfs1.getFirst();
//        d.setAge(21);
//        d.save();
//        LinkedList<StuInf> stuInfs2=StuInf.filter("Sex=true");
//        StuInf e=stuInfs2.getFirst();
//        e.delete();


    }
}
