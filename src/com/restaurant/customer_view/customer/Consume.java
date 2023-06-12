package com.restaurant.customer_view.customer;

import com.restaurant.model.MenuModel;
import com.restaurant.model.Model;
import com.restaurant.tools.MyTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

public class Consume extends JFrame implements ActionListener {
    JLabel jl;
    JButton jb_ensure;
    JPanel p1,p2;
    int desk;
    String state;
    String total_price;
    int c_id;
    int num;
//    public static void main(String[] args) {
//        new Consume(15,2,"满桌");
//    }
    public Consume(CanChe CanCheWindow,Menu MenuWindow,CustomerOrderWindow COWindow,int c_id,int desk,String state) {
        this.num=0;
        this.c_id = c_id;
        this.desk=desk;
        this.state=state;
        p1=new JPanel();
        p2=new JPanel();
        MenuModel mm=new MenuModel();
        int [] params={c_id};
        mm.query("select sum(价格) from 餐车 where 顾客编号=?",params);
        total_price=(String)mm.getValueAt(0,0);
        System.out.println("该顾客共消费了"+total_price+"元");
        jl=new JLabel("你共消费了"+total_price+"元");
        jl.setFont(MyTools.f3);

        jb_ensure=new JButton("支付");
        jb_ensure.setFont(MyTools.f3);
        jb_ensure.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户已完成支付");
                modifyState();
                addDingdan();
                CanCheWindow.dispose();
                MenuWindow.dispose();
                COWindow.dispose();
            }
        });

        p1.add(jl);
        p2.add(jb_ensure);

        this.setLayout(new BorderLayout());
        this.add(p1,"Center");
        this.add(p2,"South");
        this.setTitle("这是你的消费账单");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width/2-200,0);
        this.setSize(400, 200);
        this.setVisible(true);

    }




    /**
     * 添加数据进订单表
     */
    public void addDingdan(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
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
            //3. 得到Statement
            String sql="insert into 订单(消费金额,顾客编号) values (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,total_price);
            preparedStatement.setInt(2,c_id);
            preparedStatement.executeUpdate();
            this.dispose();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
    /**
     * 修改餐桌状态为空闲并将就餐人数设置回0
     */
    public void modifyState(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
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
            //3. 得到Statement
            String sql="update 餐桌 set 使用状态=?,就餐人数=? where 餐桌号=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,state);
            preparedStatement.setInt(2,num);
            preparedStatement.setInt(3,desk);
            preparedStatement.executeUpdate();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
