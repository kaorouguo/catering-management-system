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
import java.util.Properties;

/**
 * 餐车页面，点击点餐完毕后跳出Consume页面
 */
public class CanChe extends JFrame implements ActionListener {
    JButton jb_select,jb_pay,jb_reflesh;
    JPanel p1,p2,p3;
    JTable jtable;
    JScrollPane jsp;
    MenuModel mm;
    int c_id;
    int desk;
    String state;

//    public static void main(String[] args) {
////        new CanChe(40,5,"满桌");
//    }

    public CanChe(Menu MenuWindow,CustomerOrderWindow COwindow,int c_id,int desk,String state){
        CanChe CanCheWindow=this;
        this.c_id = c_id;
        this.desk = desk;
        this.state = state;
        /**
         * 表格
         */
        mm=new MenuModel();
        int [] params={c_id};
        mm.query("select 菜名,价格,菜品数量 from 餐车 where 顾客编号=?",params);

        jtable=new JTable(mm);
        jsp=new JScrollPane(jtable);
        p1=new JPanel();
        p1.add(jsp);

        jb_pay=new JButton("点餐完毕");
        jb_pay.setFont(MyTools.f3);
        jb_pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyState();
                new Consume(CanCheWindow,MenuWindow,COwindow,c_id,desk,"空闲");
            }
        });

        p2=new JPanel();
//        p2.add(jb_select);
        p2.add(jb_pay);

        this.setTitle("您已选择如下菜品");
        this.setLayout(new BorderLayout());
        this.add(p1,"Center");
        this.add(p2,"South");
//        this.add(p3,"North");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width/2,height/2-300);
        this.setSize(800,600);
        this.setVisible(true);
    }
    public void modifyState(){
        System.out.println("用户提交了菜单");
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
            String sql="update 餐桌 set 使用状态=? where 餐桌号=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,state);
            preparedStatement.setInt(2,desk);
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
