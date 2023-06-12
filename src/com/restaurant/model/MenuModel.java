package com.restaurant.model;

import com.restaurant.db.SqlHelper;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


public class MenuModel extends AbstractTableModel {
        Vector<String> colums;
        Vector<Vector> rows;


        /**
         * 提供菜品编号来删除菜品的函数
         * @param menuId 提供的菜品编号
         * @return 删除是否成功
         */
        public boolean delMenuById(String menuId){
            boolean b=true;
            String sql="delete from 菜单 where 菜单编号=?";
            String []paras={menuId};
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


        public void query(String sql,String paras[]){
            //初始化列
            this.colums=new Vector<String>();
            this.rows=new Vector<Vector>();
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

    public void query(String sql,int paras[]){
        //初始化列
        this.colums=new Vector<String>();
        this.rows=new Vector<Vector>();
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

        public void init(String sql,String paras[]){//这里传入的sql要记得加and 1='';

            this.colums=new Vector<String>();
            this.rows=new Vector<Vector>();
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
            return (rows.get(rowIndex)).get(columnIndex);

        }

        @Override
        public String getColumnName(int column) {//显示列名
            return this.colums.get(column).toString();
        }
}

