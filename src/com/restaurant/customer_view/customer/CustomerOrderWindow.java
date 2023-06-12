package com.restaurant.customer_view.customer;

import com.restaurant.model.MenuModel;
import com.restaurant.model.Model;
import com.restaurant.tools.ImagePanel;
import com.restaurant.tools.MyTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 选择餐桌页面，选定餐桌后，弹出菜单Menu界面
 */
public class CustomerOrderWindow extends JFrame implements MouseListener {
    JPanel jp1,jp2;
    JLabel jl1_cz1,jl1_cz2,jl1_cz3,jl1_cz4,jl1_cz5,jl1_cz6,jl1_cz7,jl1_cz8,jl1_cz9;
    ImagePanel jp1_imagePanel;
    String phone;
    String c_id;
    String state;
    String number;
//    public static void main(String[] args) {
//        new CustomerOrderWindow("13797220670");
//    }

    /**
     * 餐桌界面
     * @param phone 接受CustomerIndex传来的电话，方便查找顾客编号
     */
    public CustomerOrderWindow(String phone,String number) {
        this.phone = phone;
        this.number=number;
        jl1_cz1=new JLabel("1号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz1.setFont(MyTools.f4);
        jl1_cz1.addMouseListener(this);
        jl1_cz2=new JLabel("2号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz2.setFont(MyTools.f4);
        jl1_cz2.addMouseListener(this);
        jl1_cz3=new JLabel("3号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz3.setFont(MyTools.f4);
        jl1_cz3.addMouseListener(this);
        jl1_cz4=new JLabel("4号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz4.setFont(MyTools.f4);
        jl1_cz4.addMouseListener(this);
        jl1_cz5=new JLabel("5号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz5.setFont(MyTools.f4);
        jl1_cz5.addMouseListener(this);
        jl1_cz6=new JLabel("6号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz6.setFont(MyTools.f4);
        jl1_cz6.addMouseListener(this);
        jl1_cz7=new JLabel("7号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz7.setFont(MyTools.f4);
        jl1_cz7.addMouseListener(this);
        jl1_cz8=new JLabel("8号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz8.setFont(MyTools.f4);
        jl1_cz8.addMouseListener(this);
        jl1_cz9=new JLabel("9号餐桌",new ImageIcon("image/餐桌1.png"),0);
        jl1_cz9.setFont(MyTools.f4);
        jl1_cz9.addMouseListener(this);

        /**
         * 处理jp1面板
         */
        jp1=new JPanel(new BorderLayout());
        Image jp1_bg= null;
        try {
            jp1_bg = ImageIO.read(new File("image/浅蓝色背景.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jp1_imagePanel=new ImagePanel(jp1_bg);
        this.jp1_imagePanel.setLayout(new GridLayout(3,3));
        jp1_imagePanel.add(jl1_cz1);
        jp1_imagePanel.add(jl1_cz2);
        jp1_imagePanel.add(jl1_cz3);
        jp1_imagePanel.add(jl1_cz4);
        jp1_imagePanel.add(jl1_cz5);
        jp1_imagePanel.add(jl1_cz6);
        jp1_imagePanel.add(jl1_cz7);
        jp1_imagePanel.add(jl1_cz8);
        jp1_imagePanel.add(jl1_cz9);

        jp1.add(jp1_imagePanel);
        this.add(jp1);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //关闭窗口时，退出系统
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setIconImage(titleIcon);
        this.setTitle("小小西瓜餐馆欢迎您");
        this.setSize(width, height-50);
        this.setVisible(true);

        Model customermodel=new Model();
        String [] params={phone};
        customermodel.query("select * from 顾客 where 联系电话=?",params);
        c_id=(String)customermodel.getValueAt(0,0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==jl1_cz1){
            int desk=1;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz2 ){
            int desk=2;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz3){
            int desk=3;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz4){
            int desk=4;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz5 ){
            int desk=5;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz6 ){
            int desk=6;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz7){
            int desk=7;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz8){
            int desk=8;
            EnsureState(desk);
        }else if(e.getSource()==jl1_cz9){
            int desk=9;
            EnsureState(desk);
        }
    }

    /**
     * 封装餐桌使用状态的确认
     */
    public void EnsureState(int desk){
        MenuModel model=new MenuModel();
        int [] params={desk};
        model.query("select 使用状态 from 餐桌 where 餐桌号=?",params);
        state=(String)model.getValueAt(0,0);
        System.out.println(state);
        if(state.equals("空闲")){
            new Menu(this,desk,phone);
            addDeskToCustomer(desk);
        }else if(state.equals("满桌")){
            JOptionPane.showMessageDialog(this,"抱歉，该餐桌已被占用","提示",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * 写一个函数，把就餐桌号添加到顾客表中
     * @param desk 餐桌号
     */
    public void addDeskToCustomer(int desk){
        Model customermodel=new Model();
        String [] params={phone};
        customermodel.query("select 顾客编号 from 顾客 where 联系电话=?",params);
        String c_id=(String)customermodel.getValueAt(0,0);
        System.out.print(c_id+"号顾客将 ");

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
            String sql="update 顾客 set 就餐桌号=? where 顾客编号=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,desk);
            preparedStatement.setInt(2,Integer.parseInt(c_id));
            preparedStatement.executeUpdate();

            String sql2="update 餐桌 set 就餐人数=? where 餐桌号=?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,Integer.parseInt(number));
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
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
