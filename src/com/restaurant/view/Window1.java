package com.restaurant.view;

import com.restaurant.tools.ImagePanel;
import com.restaurant.tools.MyTools;
import com.restaurant.view.customerinfo.CustomerInfo;
import com.restaurant.view.empinfo.EmpInfo;
import com.restaurant.view.menuinfo.MenuInfo;
import com.restaurant.view.logininfo.LoginInfo;
import com.restaurant.view.orderinfo.OrderInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * 这是系统管理员，经理或者主管可以进入的操作界面
 */
public class Window1 extends JFrame implements ActionListener, MouseListener {
    Image titleIcon,timeGg;
    JMenuBar jmb;
    //一级菜单
    JMenu jm1,jm2,jm3,jm4,jm5,jm6;
    //二级菜单
    JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;

    //图标
//    ImageIcon jm1_icon;
    ImageIcon jmm1_icon1,jmm1_icon2,jmm1_icon3,jmm1_icon4,jmm1_icon5;

    //工具栏
    JToolBar jtb;
    JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;

    //定义需要的5个面板
    JPanel jp1,jp2,jp3,jp4,jp5;
    //显示当前时间
    JLabel timeNow;
    //给jp1面板定义需要的JLabel
    JLabel jp1_lab1,jp1_lab2,jp1_lab3,jp1_lab4,jp1_lab5,jp1_lab6,jp1_lab7,jp1_lab8;
    //给jp2面板定义需要的JLabel
    JLabel jp2_lab1,jp2_lab2;
    //javax.swing.Timer可以定时触发Action事件，可以用它来完成一些事情
    javax.swing.Timer t;

    ImagePanel jp1_imgPanel;

    //用于拆分窗口
    JSplitPane jsp1;

    //卡片布局
    CardLayout cardP3;
    public static void main(String[] args) {
        Window1 window1 = new Window1();
    }

    /**
     * 初始化菜单
     */
    public void initMenu(){
        /**
         * 创建图标
         */
        jmm1_icon1=new ImageIcon("image/切换用户.png");
        jmm1_icon2=new ImageIcon("image/收款.png");
        jmm1_icon3=new ImageIcon("image/登录管理.png");
        jmm1_icon4=new ImageIcon("image/日历.png");
        jmm1_icon5=new ImageIcon("image/退出.png");

//        jm1_icon=new ImageIcon("image/系统管理.png");
        /**
         * 创建一级菜单
         */
        jm1=new JMenu("系统管理");
        jm1.setFont(MyTools.f1);
        //创建系统管理的二级菜单
        jmm1=new JMenuItem("切换用户",jmm1_icon1);
        jmm1.setFont(MyTools.f2);
        jmm1.addMouseListener(this);

//        jmm2=new JMenuItem("切换到收款界面",jmm1_icon2);
//        jmm2.setFont(MyTools.f2);

        jmm3=new JMenuItem("登陆管理",jmm1_icon3);
        jmm3.setFont(MyTools.f2);
        jmm3.addMouseListener(this);

//        jmm4=new JMenuItem("万年历",jmm1_icon4);
//        jmm4.setFont(MyTools.f2);

        jmm5=new JMenuItem("退出",jmm1_icon5);
        jmm5.setFont(MyTools.f2);
        jmm5.addMouseListener(this);

        //加入一级菜单
        jm1.add(jmm1);
//        jm1.add(jmm2);
        jm1.add(jmm3);
//        jm1.add(jmm4);
        jm1.add(jmm5);

        jm2=new JMenu("人事管理");
        jm2.setFont(MyTools.f1);
        jm2.addMouseListener(this);

        jm3=new JMenu("菜单管理");
        jm3.setFont(MyTools.f1);
        jm3.addMouseListener(this);

        jm4=new JMenu("顾客详情");
        jm4.setFont(MyTools.f1);
        jm4.addMouseListener(this);

        jm5=new JMenu("查看订单");
        jm5.setFont(MyTools.f1);
        jm5.addMouseListener(this);
//
//        jm6=new JMenu("帮助");
//        jm6.setFont(MyTools.f1);


        //把一级菜单加入JMenuBar
        jmb=new JMenuBar();
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
        jmb.add(jm4);
        jmb.add(jm5);
//        jmb.add(jm6);

        //把JMenuBar添加到JFrame
        this.setJMenuBar(jmb);//不能简单的用this.add(jmb);
    }

    /**
     * 初始化工具栏
     */
    public void initToolBar(){
        //处理工具栏的组件
        jtb=new JToolBar();
        //设置工具栏不可移动
        jtb.setFloatable(false);
        jb1=new JButton(new ImageIcon(""));
        jb2=new JButton(new ImageIcon(""));
        jb3=new JButton(new ImageIcon(""));
        jb4=new JButton(new ImageIcon(""));
        jb5=new JButton(new ImageIcon(""));
        jb6=new JButton(new ImageIcon(""));
        jb7=new JButton(new ImageIcon(""));
        jb8=new JButton(new ImageIcon(""));
        jb9=new JButton(new ImageIcon(""));
        jb10=new JButton(new ImageIcon(""));
        //把按钮加入到jtb
        jtb.add(jb1);
        jtb.add(jb2);
        jtb.add(jb3);
        jtb.add(jb4);
        jtb.add(jb5);
        jtb.add(jb6);
        jtb.add(jb7);
        jtb.add(jb8);
        jtb.add(jb9);
        jtb.add(jb10);
    }

    /**
     * 初始化中间的面板
     */
    public void initallPanel(){
        /**
         * 处理jp1面板
         */
        jp1=new JPanel(new BorderLayout());
        Image jp1_bg= null;
        try {
            jp1_bg = ImageIO.read(new File("image/云朵背景.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //建立手型光标
        Cursor myCursor = new Cursor(Cursor.HAND_CURSOR);

        this.jp1_imgPanel=new ImagePanel(jp1_bg);
        this.jp1_imgPanel.setLayout(new GridLayout(6,1));
        //jp1的一个label
        jp1_lab1=new JLabel("餐饮管理系统",new ImageIcon("image/系统菜单.png"),0);
        jp1_lab1.setFont(MyTools.f4);//设置字体
        jp1_imgPanel.add(jp1_lab1);

        jp1_lab2=new JLabel("人 事 管 理",new ImageIcon("image/人事管理.png"),0);
        jp1_lab2.setFont(MyTools.f3);
        jp1_lab2.setCursor(myCursor);
        //让该Label不可用
        jp1_lab2.setEnabled(false);
        jp1_lab2.addMouseListener(this);
        jp1_imgPanel.add(jp1_lab2);

        jp1_lab3=new JLabel("登 录 管 理",new ImageIcon("image/登录管理(主界面).png"),0);
        jp1_lab3.setFont(MyTools.f3);
        jp1_lab3.setCursor(myCursor);
        //让该Label不可用
        jp1_lab3.setEnabled(false);
        jp1_lab3.addMouseListener(this);
        jp1_imgPanel.add(jp1_lab3);

        jp1_lab4=new JLabel("菜 品 管 理",new ImageIcon("image/菜品管理.png"),0);
        jp1_lab4.setFont(MyTools.f3);
        jp1_lab4.setCursor(myCursor);
        //让该Label不可用
        jp1_lab4.setEnabled(false);
        jp1_lab4.addMouseListener(this);
        jp1_imgPanel.add(jp1_lab4);

        jp1_lab5=new JLabel("顾 客 详 情",new ImageIcon("image/人事管理.png"),0);
        jp1_lab5.setFont(MyTools.f3);
        jp1_lab5.setCursor(myCursor);
        //让该Label不可用
        jp1_lab5.setEnabled(false);
        jp1_lab5.addMouseListener(this);
        jp1_imgPanel.add(jp1_lab5);

        jp1_lab6=new JLabel("查 看 订 单",new ImageIcon("image/人事管理.png"),0);
        jp1_lab6.setFont(MyTools.f3);
        jp1_lab6.setCursor(myCursor);
        //让该Label不可用
        jp1_lab6.setEnabled(false);
        jp1_lab6.addMouseListener(this);
        jp1_imgPanel.add(jp1_lab6);


        jp1.add(this.jp1_imgPanel);

        /**
         * 处理jp4面板
         */
        jp4=new JPanel(new BorderLayout());
        /**
         * 处理jp3面板
         */
        this.cardP3=new CardLayout();
        jp3=new JPanel(this.cardP3);
        //先给jp3加入一个主界面的卡片ImagePanel
        Image main_image=null;
        try {
            main_image=ImageIO.read(new File("image/白月魁.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 主界面卡片分布
         */
        ImagePanel ip=new ImagePanel(main_image);
        jp3.add(ip,"0");
        //给jp3添加几个JLabel
        //创建EmpInfo对象(JPanel)

        EmpInfo eInfo=new EmpInfo(this);//人事管理
        jp3.add(eInfo,"1");

        LoginInfo loginInfo=new LoginInfo(this);//登录管理
        jp3.add(loginInfo,"2");

        MenuInfo foodInfo=new MenuInfo(this);
        jp3.add(foodInfo,"3");

        CustomerInfo customerInfo=new CustomerInfo(this);
        jp3.add(customerInfo,"4");

        OrderInfo orderInfo=new OrderInfo(this);
        jp3.add(orderInfo,"5");

        /**
         * 处理jp2面板
         */
        jp2=new JPanel(new CardLayout());
//        jp2_lab1=new JLabel(new ImageIcon("image/向左展开.png"));
//        jp2_lab2=new JLabel(new ImageIcon("image/向右展开.png"));
        //把jp2_lab1加入jp2中
//        jp2.add(jp2_lab1,"0");
//        jp2.add(jp2_lab2,"1");

        //把jp2,jp3加入jp4
        jp4.add(jp2,"West");
        jp4.add(jp3,"Center");

        /**
         * 做一个拆分窗口，分别存放jp1,jp4
         */
        jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
        //指定左边面板占多大
        jsp1.setDividerLocation(360);
        //把边界线宽度设为0
//        jsp1.setDividerSize(0);
    }
    public Window1(){

        //创建组件
        try {
            titleIcon= ImageIO.read(new File("image/窗口.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //调用初始化菜单函数
        this.initMenu();

        //调用初始化工具栏函数
        this.initToolBar();

        //调用初始化中间面板的函数
        this.initallPanel();

        /**
         * 处理jp5面板,显示时间
         */
        jp5=new JPanel(new BorderLayout());
        //创建Timer
        t=new Timer(1000,this);//每隔1秒触发ActionEvent
        //启动该计时器
        t.start();

        timeNow=new JLabel(Calendar.getInstance().getTime().toString());
        timeNow.setFont(MyTools.f3);

        try {
            timeGg=ImageIO.read(new File("image/状态栏.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImagePanel ip1=new ImagePanel(timeGg);
        ip1.setLayout(new BorderLayout());
        ip1.add(timeNow,"East");
        jp5.add(ip1);

        //从JFrame中取得Container
        Container ct=this.getContentPane();
        ct.add(jtb,"North");
        ct.add(jsp1,"Center");
        ct.add(jp5,"South");

        //设置大小
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //关闭窗口时，退出系统
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(titleIcon);
        this.setTitle("餐饮管理系统————为干饭而生");
        this.setSize(width, height-50);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timeNow.setText("当前时间:"+Calendar.getInstance().getTime().toLocaleString());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //判断用户点击了哪个JLabel
        if(e.getSource()==this.jp1_lab2||e.getSource()==jm2){//人事管理
            this.cardP3.show(jp3,"1");
        }else if(e.getSource()==this.jp1_lab3){//登录管理
            this.cardP3.show(jp3,"2");
        }else if(e.getSource()==this.jp1_lab4||e.getSource()==jm3){//菜品管理
            this.cardP3.show(jp3,"3");
        }else if(e.getSource()==this.jp1_lab5||e.getSource()==jm4){//顾客详情
            this.cardP3.show(jp3,"4");
        }else if(e.getSource()==this.jp1_lab6||e.getSource()==jm5){//查看订单
            this.cardP3.show(jp3,"5");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==jmm1){//切换用户
            new UserLogin();
            this.dispose();
        }else if(e.getSource()==jmm3) {//登陆管理
            this.cardP3.show(jp3,"2");
        }else if(e.getSource()==jmm5){//退出
            this.dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //如果用户选择了某个操作JLabel，则高亮
        if(e.getSource()==this.jp1_lab2){
            this.jp1_lab2.setEnabled(true);
        }else if(e.getSource()==this.jp1_lab3){
            this.jp1_lab3.setEnabled(true);
        }else if(e.getSource()==this.jp1_lab4){
            this.jp1_lab4.setEnabled(true);
        }else if(e.getSource()==this.jp1_lab5){
            this.jp1_lab5.setEnabled(true);
        }else if(e.getSource()==this.jp1_lab6){
            this.jp1_lab6.setEnabled(true);
        }else if(e.getSource()==this.jp1_lab7){
            this.jp1_lab7.setEnabled(true);
        }else if(e.getSource()==this.jp1_lab8){
            this.jp1_lab8.setEnabled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //如果用户鼠标离开了某个操作JLabel，则变暗
        if(e.getSource()==this.jp1_lab2){
            this.jp1_lab2.setEnabled(false);
        }else if(e.getSource()==this.jp1_lab3){
            this.jp1_lab3.setEnabled(false);
        }else if(e.getSource()==this.jp1_lab4){
            this.jp1_lab4.setEnabled(false);
        }else if(e.getSource()==this.jp1_lab5){
            this.jp1_lab5.setEnabled(false);
        }else if(e.getSource()==this.jp1_lab6){
            this.jp1_lab6.setEnabled(false);
        }else if(e.getSource()==this.jp1_lab7){
            this.jp1_lab7.setEnabled(false);
        }else if(e.getSource()==this.jp1_lab8){
            this.jp1_lab8.setEnabled(false);
        }
    }
}
