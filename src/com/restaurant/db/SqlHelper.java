package com.restaurant.db;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 对数据库操作的类
 */
public class SqlHelper {
    //定义需要的对象
    PreparedStatement preparedStatement=null;
    Connection connection=null;
    ResultSet resultSet=null;

    //构造函数，初始化connection
    public SqlHelper(){
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properties"));
            //获取相关的值
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            //1. 注册驱动
            Class.forName(driver);//建议写上
            //2. 得到连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //对数据库的增删改
    public boolean exeUpdate(String sql,String[] paras){
        boolean b=true;
        try {
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                preparedStatement.setString(i+1,paras[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            b=false;
            e.printStackTrace();
        }
        return b;
    }

    /**
     * @param sql 要在数据库中执行的语句
     * @param paras 需要填入的属性
     * @return 数据库中的表的数据
     */
    public ResultSet query(String sql, String[] paras){
        try {
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                preparedStatement.setString(i+1,paras[i]);
            }
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet query(String sql, int [] paras){
        try {
            preparedStatement=connection.prepareStatement(sql);
            for (int i = 0; i < paras.length; i++) {
                preparedStatement.setInt(i+1,paras[i]);
            }
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //关闭资源的方法
    public void close(){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(preparedStatement!=null) {
                preparedStatement.close();
            }
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
