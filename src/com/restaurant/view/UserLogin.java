package com.restaurant.view;

import com.restaurant.model.UserModel;
import com.restaurant.tools.MyTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 用户登录界面
 */
public class UserLogin extends JDialog implements ActionListener {

    JLabel jl1,jl2,jl3;
    JTextField jName;
    JPasswordField jPassword;
    JButton jConf,jCancel;

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
    }

    public UserLogin() {
        //把一个组件放入JFrame或者JDialog可以直接放，也可以先放到Container
        Container ct=this.getContentPane();
        //空布局
        this.setLayout(null);
        //创建各组件
        jl1=new JLabel("请输入用户名：");
//        jl1.setBounds(60,190,150,30);
        jl1.setBounds(60,140,150,30);
        jl1.setFont(MyTools.f1);
        ct.add(jl1);

        jName=new JTextField(20);
        jName.setFocusable(true);
//        jName.setBounds(180,190,120,30);
        jName.setBounds(180,140,120,30);
        jName.setBorder(BorderFactory.createLoweredBevelBorder());//下凹
        ct.add(jName);

        jl2=new JLabel("(或员工号)");
        jl2.setForeground(Color.red);//前景色
        jl2.setFont(MyTools.f2);
//        jl2.setBounds(100,210,100,30);
        jl2.setBounds(100,160,100,30);
        ct.add(jl2);

        jl3=new JLabel("请输入密码：");
//        jl3.setBounds(60,240,150,30);
        jl3.setBounds(60,190,150,30);
        jl3.setFont(MyTools.f1);
        ct.add(jl3);

        jPassword=new JPasswordField(20);
//        jPassword.setBounds(180,240,120,30);
        jPassword.setBounds(180,190,120,30);

        jPassword.setBorder(BorderFactory.createLoweredBevelBorder());
        ct.add(jPassword);

        jConf=new JButton("确定");
        jConf.setFont(MyTools.f1);
//        jConf.setBounds(110,300,70,30);
        jConf.setBounds(110,250,70,30);

        ct.add(jConf);
        jConf.addActionListener(this);

        jCancel=new JButton("取消");
//        jCancel.setBounds(210,300,70,30);
        jCancel.setBounds(210,250,70,30);

        ct.add(jCancel);
        jCancel.addActionListener(this);

        //创建一个BackImage对象
        BackImage bi = new BackImage();

        //把位置确定
        bi.setBounds(0,0,360,360);
        //不使用上下框
        ct.add(bi);
        //this.add(bi);
        this.setUndecorated(true);
        this.setSize(360,360);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * 响应用户登录请求
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jConf){
            //取出员工号，密码
            String u=this.jName.getText().trim();
            String p=new String(this.jPassword.getPassword());
            UserModel um=new UserModel();
            String res=um.checkUser(u,p);
            //调试
            System.out.println(res);

            if(res.equals("系统管理员")||res.equals("经理")){
                new Window1();
                this.dispose();
            }else if(res.equals("员工")){
                new Windows2();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"你无权登录或用户名密码错误","登陆提示",JOptionPane.ERROR_MESSAGE);
                return ;
            }
        }else if(e.getSource()==jCancel){
            this.dispose();
            System.exit(0);
        }
    }

    private class BackImage extends JPanel{
        Image im;
        public BackImage(){
            try {
                im= ImageIO.read(new File("image/login.jpg"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void paintComponent(Graphics g){
            g.drawImage(im,0,0,360,360,this);
        }
    }

}
