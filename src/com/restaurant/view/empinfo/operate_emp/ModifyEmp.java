package com.restaurant.view.empinfo.operate_emp;

import com.restaurant.model.EmpModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ModifyEmp extends JDialog implements ActionListener {
    JLabel jl_name,jl_age,jl_ID,jl_phone,jl_job,jl_EmpNum,jl_sex,jl_pwd;
    JTextField jtf_name,jtf_age,jtf_ID,jtf_phone,jtf_job,jtf_EmpNum,jtf_sex,jtf_pwd;
    JButton jb_modify,jb_cancel;
    JPanel jp1_attribute,jp2_data,jp3_button;

    /**
     *
     * @param owner
     * @param title
     * @param model
     * @param em 这里传进来的em模型是没有详细信息的那个表，即只有部分信息
     * @param selectRow
     */
    public ModifyEmp(Frame owner, String title, boolean model, EmpModel em, int selectRow){
        super(owner,title,model);
        jl_EmpNum=new JLabel("员工号");
        jl_name=new JLabel("姓名");
        jl_sex=new JLabel("性别");
        jl_age=new JLabel("年龄");
        jl_ID=new JLabel("身份证号");
        jl_phone=new JLabel("联系电话");
        jl_job=new JLabel("职位");
        jl_pwd=new JLabel("密码");

        //初始化数据
        jtf_EmpNum=new JTextField();
        jtf_EmpNum.setText((String)em.getValueAt(selectRow,0));
        jtf_EmpNum.setEditable(false);

        jtf_name=new JTextField();
//        jtf_name.setText((String)em.getValueAt(selectRow,1));

        jtf_sex=new JTextField();
//        jtf_sex.setText((String)em.getValueAt(selectRow,2));

        jtf_age=new JTextField();
//        jtf_age.setText((String)em.getValueAt(selectRow,3));

        jtf_ID=new JTextField();
//        jtf_ID.setText((String)em.getValueAt(selectRow,4));

        jtf_phone=new JTextField();
//        jtf_phone.setText((String)em.getValueAt(selectRow,5));

        jtf_job=new JTextField();
//        jtf_job.setText((String)em.getValueAt(selectRow,6));

        jtf_pwd=new JTextField();
//        jtf_pwd.setText((String)em.getValueAt(selectRow,7));

        jb_modify=new JButton("修改信息");
        jb_modify.addActionListener(this);

        jb_cancel=new JButton("取消");
        jb_cancel.addActionListener(this);

        jp1_attribute=new JPanel();
        jp2_data=new JPanel();
        jp3_button=new JPanel();

        //列名顺序
        jp1_attribute.add(jl_EmpNum);//员工号（只读）
        jp1_attribute.add(jl_name);
        jp1_attribute.add(jl_sex);
        jp1_attribute.add(jl_age);
        jp1_attribute.add(jl_ID);
        jp1_attribute.add(jl_phone);
        jp1_attribute.add(jl_job);
        jp1_attribute.add(jl_pwd);

        jp2_data.add(jtf_EmpNum);
        jp2_data.add(jtf_name);
        jp2_data.add(jtf_sex);
        jp2_data.add(jtf_age);
        jp2_data.add(jtf_ID);
        jp2_data.add(jtf_phone);
        jp2_data.add(jtf_job);
        jp2_data.add(jtf_pwd);

        jp3_button.add(jb_modify);
        jp3_button.add(jb_cancel);


        jp1_attribute.setLayout(new GridLayout(8,1));
        jp2_data.setLayout(new GridLayout(8,1));
        this.add(jp3_button, BorderLayout.SOUTH);
        this.add(jp2_data,BorderLayout.CENTER);
        this.add(jp1_attribute,BorderLayout.WEST);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds(width/2-250,height/2-125,500,250);
        this.setVisible(true);
        this.setEnabled(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb_modify){
            Connection connection=null;
            PreparedStatement preparedStatement=null;
            PreparedStatement preparedStatement2=null;
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
                String sql="update 人事资料 set 姓名=?,性别=?,年龄=?,身份证号=?,联系电话=?,职位=? where 员工号=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,jtf_name.getText());
                preparedStatement.setString(2,jtf_sex.getText());
                preparedStatement.setInt(3,Integer.parseInt(jtf_age.getText()));
                preparedStatement.setString(4,jtf_ID.getText());
                preparedStatement.setString(5,jtf_phone.getText());
                preparedStatement.setString(6,jtf_job.getText());
                preparedStatement.setInt(7,Integer.parseInt(jtf_EmpNum.getText()));

                preparedStatement.executeUpdate();

//                String sql1="insert into 登录(员工号,密码,用户姓名) values(?,?,?)";
//                preparedStatement2 = connection.prepareStatement(sql1);
//                preparedStatement2.setInt(1,Integer.parseInt(jtf_EmpNum.getText()));
//                preparedStatement2.setString(2,jtf_pwd.getText());
//                preparedStatement2.setString(3,jtf_name.getText());
//                preparedStatement2.executeUpdate();
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
