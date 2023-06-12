package com.restaurant.view.empinfo;

import com.restaurant.model.EmpModel;
import com.restaurant.tools.MyTools;
import com.restaurant.view.Window1;
import com.restaurant.view.Windows2;
import com.restaurant.view.empinfo.operate_emp.AddEmp;
import com.restaurant.view.empinfo.operate_emp.ModifyEmp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 显示人事管理的界面
 */
public class EmpInfo extends JPanel implements ActionListener {

    //定义需要的组件
    JPanel p1,p2,p3,p4,p5;
    JLabel p1_lab1,p3_lab1;
    JTextField p1_jtf1;
    JButton p1_jb1,p1_jb1_refresh,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
    //用于显示人事信息的表格
    JTable jtable;
    //滚动面板
    JScrollPane jsp;

    EmpModel em;//做类的成员变量
    public EmpInfo(Window1 window1){
        //创建需要的组件
        p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1_lab1=new JLabel("请输入姓名(或员工号)");
        p1_lab1.setFont(MyTools.f3);
        p1_jtf1=new JTextField(20);

        /**
         * 查询
         */
        p1_jb1=new JButton("查询");
        p1_jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = p1_jtf1.getText().trim();//把文本框中的内容赋给name
                String id = p1_jtf1.getText().trim();//把文本框中的内容赋给id

                //写一个sql语句
//                String sql = "select * from 人事资料 where stuName='" + name + "' and 1 =? ";
                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.init("select * from 人事资料 where 姓名='"+name+"' and 1=?",params);//精准查询
//                em.init("select * from 人事资料 where 姓名 LIKE'"+name+"%' and 1=?",params);//模糊查询
//                em.init("select * from 人事资料 where 员工号='"+id+"' and 1=?",params);//精准查询

                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        p1_jb1_refresh=new JButton("刷新");
        p1_jb1_refresh.setFont(MyTools.f3);
        p1_jb1_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1_jb1.setFont(MyTools.f3);
        //加入到p1
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(p1_jb1);
        p1.add(p1_jb1_refresh);

        /**
         * 处理中间的
         */
        em=new EmpModel();
        String [] params={"1"};
        em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
        jtable=new JTable(em);
        jtable.setSize(1024,1024);
        p2=new JPanel();
        /**
         * 将jtable放入滚动面板才能看到列名
         * p2.add(jtable);//这样不行
         */
        jsp=new JScrollPane(jtable);
        p2.add(jsp);

        //处理南部的
        p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3_lab1=new JLabel("总记录数是多少条");
        p3_lab1.setFont(MyTools.f3);
        p3.add(p3_lab1);

        p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));

        /**
         * 详细信息
         */
        p4_jb1=new JButton("详细信息");
        p4_jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要查询详细信息");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要查看详细信息的人员","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                String empNo=(String)em.getValueAt(selectedRow,0);//获得选中行的第一列数据
                System.out.println("员工编号："+empNo);
                new DetailEmp(window1,"查看详细信息",true,em,empNo);

                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);//重新给p2面板加上更新过后的jsp
//                p2.show();
//                em=new EmpModel();//如果不重新new一个数据模型，则更新的是之前定义的EmpModel
//                //更新数据模型
//                em.query("select 员工号,姓名,性别,职位,联系电话 from 人事资料 where 1=?",params);
//                jtable.setModel(em);
            }
        });
        p4_jb1.setFont(MyTools.f3);

        /**
         * 添加
         */
        p4_jb2=new JButton("添加");
        p4_jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要添加人员信息");
                new AddEmp(window1,"添加人员信息",true);
                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        p4_jb2.setFont(MyTools.f3);

        /**
         * 修改
         */
        p4_jb3=new JButton("修改");
        p4_jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要修改人员信息");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要修改资料的人员","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }

                //-------------这两行不能对调--------------------
                //将选中的selectedRow行第0列的数据(员工号)给empNo
//                String empNo=(String)em.getValueAt(selectedRow,0);//这里的em是还没有new 的em模型，已经对rows和colums初始化，因此可以获得empNo
//                em=new EmpModel();//重新new一个em模型,这个em模型将所有信息查询出来传入ModifyEmp中
                //-------------这两行不能对调--------------------

//                String empNo=(String)em.getValueAt(selectedRow,0);//这里的em是还没有new 的em模型，已经对rows和colums初始化，因此可以获得empNo
//                System.out.println("员工编号："+empNo);
//                String [] params0={empNo};
//                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 员工号=?",params0);
                new ModifyEmp(window1,"修改信息",true,em,selectedRow);//这里传入的empNo就是员工号(int)

                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        p4_jb3.setFont(MyTools.f3);

        /**
         * 删除
         */
        p4_jb4=new JButton("删除");
        p4_jb4.addActionListener(this);
        p4_jb4.setFont(MyTools.f3);

        p4.add(p4_jb1);
        p4.add(p4_jb2);
        p4.add(p4_jb3);
        p4.add(p4_jb4);

        p5=new JPanel(new BorderLayout());

        p5.add(p3,"West");
        p5.add(p4,"East");

        //把总的JPanel设置为BorderLayout布局
        this.setLayout(new BorderLayout());
        //把p1加入总的JPanel
        this.add(p1,"North");
        this.add(p2,"Center");
        this.add(p5,"South");

//        this.setBackground(Color.pink);
        this.setVisible(true);
    }

    public EmpInfo(Windows2 window1){
        //创建需要的组件
        p1=new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1_lab1=new JLabel("请输入姓名(或员工号)");
        p1_lab1.setFont(MyTools.f3);
        p1_jtf1=new JTextField(20);

        /**
         * 查询
         */
        p1_jb1=new JButton("查询");
        p1_jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = p1_jtf1.getText().trim();//把文本框中的内容赋给name
                String id = p1_jtf1.getText().trim();//把文本框中的内容赋给id

                //写一个sql语句
//                String sql = "select * from 人事资料 where stuName='" + name + "' and 1 =? ";
                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
//                em.init("select * from 人事资料 where 姓名='"+name+"' and 1=?",params);//精准查询
                em.init("select * from 人事资料 where 姓名 LIKE'"+name+"%' and 1=?",params);//模糊查询
//                em.init("select * from 人事资料 where 员工号='"+id+"' and 1=?",params);//精准查询

                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        p1_jb1_refresh=new JButton("刷新");
        p1_jb1_refresh.setFont(MyTools.f3);
        p1_jb1_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1_jb1.setFont(MyTools.f3);
        //加入到p1
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(p1_jb1);
        p1.add(p1_jb1_refresh);

        /**
         * 处理中间的
         */
        em=new EmpModel();
        String [] params={"1"};
        em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
        jtable=new JTable(em);
        jtable.setSize(1024,1024);
        p2=new JPanel();
        /**
         * 将jtable放入滚动面板才能看到列名
         * p2.add(jtable);//这样不行
         */
        jsp=new JScrollPane(jtable);
        p2.add(jsp);

        //处理南部的
        p3=new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3_lab1=new JLabel("总记录数是多少条");
        p3_lab1.setFont(MyTools.f3);
        p3.add(p3_lab1);

        p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));

        /**
         * 详细信息
         */
        p4_jb1=new JButton("详细信息");
        p4_jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要查询详细信息");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要查看详细信息的人员","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                String empNo=(String)em.getValueAt(selectedRow,0);//获得选中行的第一列数据
                System.out.println("员工编号："+empNo);
                new DetailEmp(window1,"查看详细信息",true,em,empNo);

                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);//重新给p2面板加上更新过后的jsp
//                p2.show();
//                em=new EmpModel();//如果不重新new一个数据模型，则更新的是之前定义的EmpModel
//                //更新数据模型
//                em.query("select 员工号,姓名,性别,职位,联系电话 from 人事资料 where 1=?",params);
//                jtable.setModel(em);
            }
        });
        p4_jb1.setFont(MyTools.f3);

        /**
         * 添加
         */
        p4_jb2=new JButton("添加");
        p4_jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要添加人员信息");
                new AddEmp(window1,"添加人员信息",true);
                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        p4_jb2.setFont(MyTools.f3);

        /**
         * 修改
         */
        p4_jb3=new JButton("修改");
        p4_jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要修改人员信息");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要修改资料的人员","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }

                //-------------这两行不能对调--------------------
                //将选中的selectedRow行第0列的数据(员工号)给empNo
//                String empNo=(String)em.getValueAt(selectedRow,0);//这里的em是还没有new 的em模型，已经对rows和colums初始化，因此可以获得empNo
//                em=new EmpModel();//重新new一个em模型,这个em模型将所有信息查询出来传入ModifyEmp中
                //-------------这两行不能对调--------------------

//                String empNo=(String)em.getValueAt(selectedRow,0);//这里的em是还没有new 的em模型，已经对rows和colums初始化，因此可以获得empNo
//                System.out.println("员工编号："+empNo);
//                String [] params0={empNo};
//                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 员工号=?",params0);
                new ModifyEmp(window1,"修改信息",true,em,selectedRow);//这里传入的empNo就是员工号(int)

                em=new EmpModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
                jtable.setModel(em);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        p4_jb3.setFont(MyTools.f3);

        /**
         * 删除
         */
        p4_jb4=new JButton("删除");
        p4_jb4.addActionListener(this);
        p4_jb4.setFont(MyTools.f3);

        p4.add(p4_jb1);
        p4.add(p4_jb2);
        p4.add(p4_jb3);
        p4.add(p4_jb4);

        p5=new JPanel(new BorderLayout());

        p5.add(p3,"West");
        p5.add(p4,"East");

        //把总的JPanel设置为BorderLayout布局
        this.setLayout(new BorderLayout());
        //把p1加入总的JPanel
        this.add(p1,"North");
        this.add(p2,"Center");
        this.add(p5,"South");

//        this.setBackground(Color.pink);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==p4_jb4){//如果用户要删除某个员工
            System.out.println("用户要删除");
            int selectedRow = jtable.getSelectedRow();//返回选择的行数
            String empNo=(String)em.getValueAt(selectedRow,0);
            System.out.println("员工编号："+empNo);
            if(em.delEmpById(empNo)){
                JOptionPane.showMessageDialog(null,"删除成功");
            }else{
                JOptionPane.showMessageDialog(null,"删除失败");
            }
            String []params={"1"};
            em=new EmpModel();//如果不重新new一个数据模型，则更新的是之前定义的EmpModel
            //更新数据模型
            em.query("select 员工号,姓名,性别,年龄,联系电话 from 人事资料 where 1=?",params);
            jtable.setModel(em);
        }
    }


}

/**
 * 查看详细信息的类
 */
class DetailEmp extends JDialog {
    //用于显示人事信息的表格
    JTable jtable1;
    //滚动面板
    JScrollPane jsp1;

    JPanel p1;
    public DetailEmp(Frame owner, String title, boolean model, EmpModel em, String empNo){
        super(owner, title, model);
//        String [] params={empNo+""};
        String [] params={empNo};

        em.query("select * from 人事资料 where 员工号=?",params);
        jtable1=new JTable(em);
        jsp1=new JScrollPane(jtable1);
        p1=new JPanel();
        p1.add(jsp1);
        this.add(p1,"Center");
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds(width/2-500,height/2-250,1000,500);
        this.setVisible(true);
    }
}