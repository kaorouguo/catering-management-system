package com.restaurant.model;

import com.restaurant.db.SqlHelper;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 人事数据模型类，完成对人事表的各种操作
 */

/**
 * 每次newEmpModel对象时，记得调用query方法对colums和rows赋值，否则抛异常
 */
public class EmpModel extends AbstractTableModel{
    Vector<String> colums;
    Vector<Vector> rows;

    /**
     * 提供员工号来删除员工的函数
     * @param empId 提供的员工号
     * @return 删除是否成功
     */
    public boolean delEmpById(String empId){
        boolean b=true;
        String sql="delete from 人事资料 where 员工号=?";
        String []paras={empId};
        SqlHelper sh=new SqlHelper();
        try {
            b=sh.exeUpdate(sql,paras);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sh.close();
        }
        return b;
    }
    /**
     * 写一个方法，用于查询用于显示的人事信息
     */
    //query方法不通用，对其进行修改，使其具有更好的通用性

    public void query(String sql,String paras[]){
        //初始化列
        this.colums=new Vector<String>();
//        this.colums.add("员工号");
//        this.colums.add("姓名");
//        this.colums.add("性别");
//        this.colums.add("职位");

        this.rows=new Vector<Vector>();//没懂
        //创建SqlHelper对象
        SqlHelper sh=new SqlHelper();
        ResultSet rs=sh.query(sql,paras);

        try {
            //从rs对象中可以得到一个ResultSetMetadata
            /**
             * rsmt可以得到结果有多少列，而且可以知道每列的名字
             */
            ResultSetMetaData rsmt = rs.getMetaData();
            for (int i = 0; i < rsmt.getColumnCount(); i++) {
//                this.colums.add(rsmt.getCatalogName(i+1));//得到数据库的名字
                this.colums.add(rsmt.getColumnName(i+1));//得到表中每一列的名字

            }
            //把rs的结果放入rows
            while (rs.next()){
                Vector<String> temp=new Vector<String>();//不能放在外面，不然拿到的都是相同数据
                for (int i = 0; i < rsmt.getColumnCount(); i++) {
                    temp.add(rs.getString(i+1));
                }
//                temp.add(rs.getString("员工号"));
//                temp.add(rs.getString("姓名"));
//                temp.add(rs.getString("性别"));
//                temp.add(rs.getString("职位"));
                rows.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sh.close();
        }

    }
    public void init(String sql,String paras[]){//这里传入的sql要记得加and 1='';

        this.colums=new Vector<String>();
        this.rows=new Vector<Vector>();
//        String [] paras={"1"};
        SqlHelper sh=new SqlHelper();
        ResultSet rs=sh.query(sql,paras);
        try {
            //从rs对象中可以得到一个ResultSetMetadata
            /**
             * rsmt可以得到结果有多少列，而且可以知道每列的名字
             */
            ResultSetMetaData rsmt = rs.getMetaData();
            for (int i = 0; i < rsmt.getColumnCount(); i++) {
//                this.colums.add(rsmt.getCatalogName(i+1));//得到数据库的名字
                this.colums.add(rsmt.getColumnName(i+1));//得到表中每一列的名字

            }
            //把rs的结果放入rows
            while (rs.next()){
                Vector<String> temp=new Vector<String>();//不能放在外面，不然拿到的都是相同数据
                for (int i = 0; i < rsmt.getColumnCount(); i++) {
                    temp.add(rs.getString(i+1));
                }
//                temp.add(rs.getString("员工号"));
//                temp.add(rs.getString("姓名"));
//                temp.add(rs.getString("性别"));
//                temp.add(rs.getString("职位"));
                rows.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sh.close();
        }

    }
//    public EmpModel(){
//        this.init("");
//    }
//    public EmpModel(String sql){
//        this.init(sql);
//    }

    /**
     * 每次new一个新的EmpModel对象时，都要有一个给colums和rows集合赋值的操作，不然下面重写的rows和colums就会报空指针异常
     */
//    public void assignValuesToColumnsAndRows(){
//
//    }
    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    @Override
    public int getColumnCount() {
        return this.colums.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        return ((Vector)rows.get(rowIndex)).get(columnIndex);
        return (rows.get(rowIndex)).get(columnIndex);

    }

    @Override
    public String getColumnName(int column) {//显示列名
        return this.colums.get(column).toString();
    }
}
