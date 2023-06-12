package com.restaurant.customer_view.customer;


import com.restaurant.tools.ImagePanel;
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
 * 起始页面,收取顾客联系电话和就餐人数信息并点击点餐后跳转CustomerOrderWindow页面
 */
public class CustomerIndex extends JFrame implements ActionListener {
    Image image;
    JPanel jp,jp_phone,jp_person;
    JTextField jp_phone_jtf,jtf_person;
    JLabel jp_phone_jl,jl_person;
    JButton jb;
    String phone;//传给点餐的那个页面
    String number;
    public static void main(String[] args) {
        CustomerIndex index = new CustomerIndex();
    }

    public CustomerIndex() {
        jp_phone_jl=new JLabel("请输入联系电话");
        jp_phone_jl.setFont(MyTools.f3);
        jp_phone_jtf=new JTextField(20);
        jb = new JButton("点餐");
        jb.addActionListener(this);
        jb.setFont(MyTools.f3);
        jp_phone=new JPanel();
        jp_phone.add(jp_phone_jl);
        jp_phone.add(jp_phone_jtf);
        jp_phone.add(jb);

        jp_person=new JPanel();
        jl_person=new JLabel("请输入就餐人数");
        jl_person.setFont(MyTools.f3);
        jtf_person=new JTextField(20);
        jp_person.add(jl_person);
        jp_person.add(jtf_person);

        jp = new JPanel(new BorderLayout());

        image = Toolkit.getDefaultToolkit().getImage("image/二维码.png");//背景图片
        ImagePanel ip = new ImagePanel(image);

        jp.add(jp_phone,"South");
        jp.add(ip, "Center");
        jp.add(jp_person,"North");


        this.add(jp);
        this.setSize(500, 500);
        /**
         * 确定JWindow的初始位置
         */
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb){
            phone = jp_phone_jtf.getText();
            number= jtf_person.getText();
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
                String sql="insert into 顾客(联系电话) values (?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,phone);
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
            new CustomerOrderWindow(phone,number);
        }
    }
}