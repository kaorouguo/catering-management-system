package com.restaurant.view.menuinfo.operate_food;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class AddMenu extends JDialog implements ActionListener {
    JLabel jl_name,jl_price;
    JTextField jtf_name,jtf_price;
    JButton jb_add,jb_cancel;
    JPanel jp1_attribute,jp2_data,jp3_button;

    public AddMenu(Frame owner, String title, boolean model) {
        super(owner, title, model);
        jl_name = new JLabel("菜名");
        jl_price = new JLabel("价格");

        jtf_name = new JTextField();
        jtf_price = new JTextField();

        jb_add = new JButton("添加");
        jb_add.addActionListener(this);

        jb_cancel = new JButton("取消");
        jb_cancel.addActionListener(this);

        jp1_attribute = new JPanel();
        jp2_data = new JPanel();
        jp3_button = new JPanel();

        jp1_attribute.add(jl_name);
        jp1_attribute.add(jl_price);

        jp2_data.add(jtf_name);
        jp2_data.add(jtf_price);

        jp3_button.add(jb_add);
        jp3_button.add(jb_cancel);


        jp1_attribute.setLayout(new GridLayout(3, 1));
        jp2_data.setLayout(new GridLayout(3, 1));
        this.add(jp3_button, BorderLayout.SOUTH);
        this.add(jp2_data, BorderLayout.CENTER);
        this.add(jp1_attribute, BorderLayout.WEST);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds(width / 2 - 250, height / 2 - 125, 500, 250);
        this.setVisible(true);
        this.setEnabled(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb_add){
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
                String sql="insert into 菜单(菜名,价格) values(?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,jtf_name.getText());
                preparedStatement.setInt(2,Integer.parseInt(jtf_price.getText()));

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
        }else if(e.getSource()==jb_cancel){
            this.dispose();
        }
    }
}
