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
 * 菜单表格，点击加入菜单按钮后弹出CanChe页面
 */
public class Menu extends JFrame implements ActionListener{
    //定义需要的组件
    /**
     * p1是最上面的查找
     * p2是中间的表格
     * p3是最下面的加入餐车，点餐完毕
     */
    JPanel p1,p2,p3,p4,p5;
    JLabel p1_lab1,p3_lab1;
    JTextField p1_jtf1;
    JButton p1_jb1,p1_jb1_refresh,p3_jb_add,p3_jb_over;
    //用于显示人事信息的表格
    JTable jtable;
    //滚动面板
    JScrollPane jsp;
    Model customermodel;
    MenuModel mm;//做类的成员变量


//    public static void main(String[] args) {
////        CustomerOrderWindow w=new CustomerOrderWindow("123456");
////        new Menu(w,"123456");
//    }

    /**
     * 点餐界面
     * @param COwindow 餐桌界面
     * @param phone 顾客的电话
     */
    public Menu(CustomerOrderWindow COwindow,int desk,String phone) {
        /**
         * 通过电话查询到顾客编号
         */
        Menu MenuWindow=this;
        customermodel=new Model();
        String [] params={phone};
        customermodel.query("select * from 顾客 where 联系电话=?",params);
        //此时customermodel模型里面只有联系方式为phone的这一行
        //下面通过getValueAt获取顾客编号即可


        //创建需要的组件
        p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1_lab1=new JLabel("请输入想查询的菜名");
        p1_lab1.setFont(MyTools.f3);
        p1_jtf1=new JTextField(20);
        /**
         * 查询
         */
        p1_jb1=new JButton("查询");
        p1_jb1.setFont(MyTools.f3);
        p1_jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = p1_jtf1.getText().trim();//把文本框中的内容赋给name
                String id = p1_jtf1.getText().trim();//把文本框中的内容赋给id

                //写一个sql语句
//                String sql = "select * from 人事资料 where stuName='" + name + "' and 1 =? ";
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.init("select 菜名,价格 from 菜单 where 菜名='"+name+"' and 1=?",params);//精准查询
//                mm.init("select * from 人事资料 where 姓名 LIKE'"+name+"%' and 1=?",params);//模糊查询
//                mm.init("select * from 人事资料 where 员工号='"+id+"' and 1=?",params);//精准查询

                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1_jb1_refresh=new JButton("刷新");
        p1_jb1_refresh.setFont(MyTools.f3);
        p1_jb1_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户点击了刷新");
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select 菜名,价格 from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        //加入到p1
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(p1_jb1);
        p1.add(p1_jb1_refresh);
//        p1.add(p1_jb1_refresh);

        /**
         * 处理中间的
         */
        mm=new MenuModel();
        String [] param={"1"};
        mm.query("select 菜名,价格 from 菜单 where 1=?",param);
        jtable=new JTable(mm);
        jtable.setSize(500,500);
        p2=new JPanel();
        /**
         * 将jtable放入滚动面板才能看到列名
         * p2.add(jtable);//这样不行
         */
        jsp=new JScrollPane(jtable);
        p2.add(jsp);
        /**
         * 加入餐车
         */
        p3_jb_add=new JButton("加入餐车");
        p3_jb_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(COwindow,"请点餐","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                String c_id=(String)customermodel.getValueAt(0,0);
//                System.out.println(c_id+"号顾客");
                String [] params={"1"};
                mm.query("select 菜名,价格 from 菜单 where 1 = ?",params);
                new MenuAddCanChe(mm,selectedRow,Integer.parseInt(c_id));
//                new CanChe(Integer.parseInt(c_id));

                new CanChe(MenuWindow,COwindow,Integer.parseInt(c_id),desk,"满桌");

                System.out.println(" 加入餐车");
            }
        });

        p3_jb_add.setFont(MyTools.f3);
//        p3_jb_over=new JButton("点餐完毕");
//        p3_jb_over.setFont(MyTools.f3);
//        p3_jb_over.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });

        p3=new JPanel();
        p3.add(p3_jb_add);
//        p3.add(p3_jb_over);


        this.setTitle("请点餐");

        this.add(p1,"North");
        this.add(p2,"Center");
        this.add(p3,"South");

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width/2-800,height/2-300);
        this.setSize(800,600);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
class MenuAddCanChe{
    Model customermodel;
    String caiming;
    String price;
    public MenuAddCanChe(MenuModel mm,int selectedRow,int c_id) {
        customermodel=new Model();
        String [] params={"1"};
        customermodel.query("select * from 顾客 where 1=?",params);

        caiming=(String)mm.getValueAt(selectedRow,0);
        System.out.print(caiming+" ");
        price=(String)mm.getValueAt(selectedRow,1);//这里的price是String类型,后面记得转换
        System.out.print(price);

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
            String sql="insert into 餐车(菜名,价格,顾客编号) values(?,?,?)";
//            String sql="update 人事资料 set 姓名=?,性别=?,年龄=?,身份证号=?,联系电话=?,职位=? where 员工号=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,caiming);
            preparedStatement.setInt(2,Integer.parseInt(price));
            preparedStatement.setInt(3,c_id);

//            preparedStatement.setInt(3,id);

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
}