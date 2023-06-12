package com.restaurant.view.logininfo;

import com.restaurant.model.LoginModel;
import com.restaurant.tools.MyTools;
import com.restaurant.view.Window1;
import com.restaurant.view.Windows2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class LoginInfo extends JPanel implements ActionListener {
    JTable jtable;
    JButton jb_modify_pwd,jb_refresh;
    JScrollPane jsp;
    JPanel jp1,jp2;

    LoginModel lm;
    public LoginInfo(Window1 window1){
        jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb_modify_pwd=new JButton("修改用户名与密码");
        jb_modify_pwd.setFont(MyTools.f3);
        jb_modify_pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("管理员要修改用户名与密码");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要修改资料的人员","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                new ModifyLogin(window1,"修改用户密码中",true,lm,selectedRow);
                lm=new LoginModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                lm.query("select * from 登录 where 1=?",params);
                jtable.setModel(lm);
                jsp=new JScrollPane(jtable);
                jp2.add(jsp);
            }
        });
        jb_refresh=new JButton("刷新");
        jb_refresh.setFont(MyTools.f3);
        jb_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lm=new LoginModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                lm.query("select * from 登录 where 1=?",params);
                jtable.setModel(lm);
                jsp=new JScrollPane(jtable);
                jp2.add(jsp);
            }
        });
        jp1.add(jb_modify_pwd);
        jp1.add(jb_refresh);

        /**
         * 处理中间的表格
         */
        lm=new LoginModel();
        String [] params={"1"};
        lm.query("select * from 登录 where 1=?",params);
        jtable=new JTable(lm);
        jtable.setSize(1024,1024);
        jp2=new JPanel();
        jsp=new JScrollPane(jtable);
        jp2.add(jsp);
        this.setLayout(new BorderLayout());
        this.setSize(1024,1024);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp1, BorderLayout.NORTH);
        this.setVisible(true);
    }

    public LoginInfo(Windows2 window1){
        jp1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        jb_modify_pwd=new JButton("修改用户名与密码");
        jb_modify_pwd.setFont(MyTools.f3);
        jb_modify_pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("管理员要修改用户名与密码");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要修改资料的人员","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                new ModifyLogin(window1,"修改用户密码中",true,lm,selectedRow);
                lm=new LoginModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                lm.query("select * from 登录 where 1=?",params);
                jtable.setModel(lm);
                jsp=new JScrollPane(jtable);
                jp2.add(jsp);
            }
        });
        jb_refresh=new JButton("刷新");
        jb_refresh.setFont(MyTools.f3);
        jb_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lm=new LoginModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                lm.query("select * from 登录 where 1=?",params);
                jtable.setModel(lm);
                jsp=new JScrollPane(jtable);
                jp2.add(jsp);
            }
        });
        jp1.add(jb_modify_pwd);
        jp1.add(jb_refresh);

        /**
         * 处理中间的表格
         */
        lm=new LoginModel();
        String [] params={"1"};
        lm.query("select * from 登录 where 1=?",params);
        jtable=new JTable(lm);
        jtable.setSize(1024,1024);
        jp2=new JPanel();
        jsp=new JScrollPane(jtable);
        jp2.add(jsp);
        this.setLayout(new BorderLayout());
        this.setSize(1024,1024);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp1, BorderLayout.NORTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class ModifyLogin extends JDialog implements ActionListener{
    JLabel jlabel_EmpNum,jlabel_name,jlabel_pwd;
    JTextField jtf_EmpNum,jtf_name,jtf_pwd;
    JButton jb_modify,jb_cancel;
    JPanel jp1_attribute,jp2_data,jp3_button;

    public ModifyLogin(Frame owner,String title,boolean model,LoginModel lm,int selectedRow){
        super(owner, title, model);
        jlabel_EmpNum=new JLabel("员工号");
        jlabel_name=new JLabel("用户姓名");
        jlabel_pwd=new JLabel("密码");

        jb_modify=new JButton("确定");
        jb_modify.addActionListener(this);
        jb_cancel=new JButton("取消");
        jb_cancel.addActionListener(this);

        jtf_EmpNum=new JTextField();
        jtf_EmpNum.setText((String)lm.getValueAt(selectedRow,0));
        jtf_EmpNum.setEditable(false);

        jtf_name=new JTextField();
        jtf_name.setText((String)lm.getValueAt(selectedRow,1));

        jtf_pwd=new JTextField();
        jtf_pwd.setText((String)lm.getValueAt(selectedRow,2));

        jb_modify=new JButton("修改信息");
        jb_modify.addActionListener(this);

        jb_cancel=new JButton("取消");
        jb_cancel.addActionListener(this);

        jp1_attribute=new JPanel();
        jp2_data=new JPanel();
        jp3_button=new JPanel();

        jp1_attribute.add(jlabel_EmpNum);
        jp1_attribute.add(jlabel_name);
        jp1_attribute.add(jlabel_pwd);

        jp2_data.add(jtf_EmpNum);
        jp2_data.add(jtf_name);
        jp2_data.add(jtf_pwd);

        jp3_button.add(jb_modify);
        jp3_button.add(jb_cancel);

        jp1_attribute.setLayout(new GridLayout(3,1));
        jp2_data.setLayout(new GridLayout(3,1));

        this.add(jp3_button, BorderLayout.SOUTH);
        this.add(jp2_data,BorderLayout.CENTER);
        this.add(jp1_attribute,BorderLayout.WEST);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds(width/2-150,height/2-75,300,150);
        this.setVisible(true);
        this.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb_modify){
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
//                String sql="insert into 人事资料(姓名,年龄,身份证号,联系电话,职位,员工号,性别) values(?,?,?,?,?,?,?)";
                String sql="update 登录 set 用户姓名=?,密码=? where 员工号=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,jtf_name.getText());
                preparedStatement.setInt(2,Integer.parseInt(jtf_pwd.getText()));
                preparedStatement.setInt(3,Integer.parseInt(jtf_EmpNum.getText()));

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