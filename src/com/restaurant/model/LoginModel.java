package com.restaurant.model;

import com.restaurant.db.SqlHelper;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class LoginModel extends AbstractTableModel{
    Vector<String> colums;
    Vector<Vector> rows;

    public void query(String sql,String paras[]){
        //初始化列
        this.colums=new Vector<String>();

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
                rows.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sh.close();
        }

    }

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
