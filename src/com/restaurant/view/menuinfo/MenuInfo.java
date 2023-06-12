package com.restaurant.view.menuinfo;

import com.restaurant.model.EmpModel;
import com.restaurant.model.MenuModel;
import com.restaurant.tools.MyTools;
import com.restaurant.view.Window1;
import com.restaurant.view.Windows2;
import com.restaurant.view.empinfo.operate_emp.AddEmp;
import com.restaurant.view.empinfo.operate_emp.ModifyEmp;
import com.restaurant.view.menuinfo.operate_food.AddMenu;
import com.restaurant.view.menuinfo.operate_food.ModifyMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInfo extends JPanel implements ActionListener {
    JPanel p1,p2,p3;
    JLabel p1_lab1;
    JTextField p1_jtf1;
    JButton jb_query,jb_refresh,jb_add,jb_modify,jb_delete;
    //用于显示人事信息的表格
    JTable jtable;
    //滚动面板
    JScrollPane jsp;
    MenuModel mm;

    public MenuInfo(Window1 window1){
        p1_lab1=new JLabel("请输入要查询的菜品名");
        p1_lab1.setFont(MyTools.f3);
        p1_jtf1=new JTextField(20);

        /**
         * 查询
         */
        jb_query=new JButton("查询");
        jb_query.setFont(MyTools.f1);
        jb_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = p1_jtf1.getText().trim();//把文本框中的内容赋给name
                String id = p1_jtf1.getText().trim();//把文本框中的内容赋给id

                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.init("select * from 菜单 where 菜名='"+name+"' and 1=?",params);//精准查询
//                mm.init("select * from 菜单 where 菜名 LIKE'"+name+"%' and 1=?",params);//模糊查询
//                mm.init("select * from 菜单 where 菜品编号='"+id+"' and 1=?",params);//精准查询

                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        /**
         * 刷新
         */
        jb_refresh=new JButton("刷新");
        jb_refresh.setFont(MyTools.f1);
        jb_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1=new JPanel();
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(jb_query);
        p1.add(jb_refresh);
        /**
         * 中间表格
         */
        jtable=new JTable();
        mm=new MenuModel();
        String [] params={"1"};
        mm.query("select * from 菜单 where 1=?",params);
        jtable=new JTable(mm);
        jtable.setSize(500,500);
        jsp=new JScrollPane(jtable);
        p2=new JPanel();
        p2.add(jsp);

        /**
         * 添加
         */
        jb_add=new JButton("添加");
        jb_add.setFont(MyTools.f3);
        jb_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要添加菜品");
                new AddMenu(window1,"添加新菜品",true);
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        /**
         * 修改
         */
        jb_modify=new JButton("修改");
        jb_modify.setFont(MyTools.f3);
        jb_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要修改菜品信息");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要修改的菜品","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                new ModifyMenu(window1,"修改菜品信息",true,mm,selectedRow);//这里传入的empNo就是员工号(int)
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        /**
         * 删除
         */
        jb_delete=new JButton("删除");
        jb_delete.setFont(MyTools.f3);
        jb_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要删除");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                String empNo=(String)mm.getValueAt(selectedRow,0);
                System.out.println("员工编号："+empNo);
                if(mm.delMenuById(empNo)){
                    JOptionPane.showMessageDialog(null,"删除成功");
                }else{
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
                String []params={"1"};
                mm=new MenuModel();//如果不重新new一个数据模型，则更新的是之前定义的EmpModel
                //更新数据模型
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
            }
        });

        p3=new JPanel();
        p3.add(jb_add);
        p3.add(jb_modify);
        p3.add(jb_delete);

        this.setLayout(new BorderLayout());
        this.add(p1,"North");
        this.add(p2,"Center");
        this.add(p3,"South");

        this.setVisible(true);
    }

    public MenuInfo(Windows2 window1){
        p1_lab1=new JLabel("请输入要查询的菜品名");
        p1_lab1.setFont(MyTools.f3);
        p1_jtf1=new JTextField(20);

        /**
         * 查询
         */
        jb_query=new JButton("查询");
        jb_query.setFont(MyTools.f1);
        jb_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户希望查询：");
                //因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
                String name = p1_jtf1.getText().trim();//把文本框中的内容赋给name
                String id = p1_jtf1.getText().trim();//把文本框中的内容赋给id

                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.init("select * from 菜单 where 菜名='"+name+"' and 1=?",params);//精准查询
//                mm.init("select * from 菜单 where 菜名 LIKE'"+name+"%' and 1=?",params);//模糊查询
//                mm.init("select * from 菜单 where 菜品编号='"+id+"' and 1=?",params);//精准查询

                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        /**
         * 刷新
         */
        jb_refresh=new JButton("刷新");
        jb_refresh.setFont(MyTools.f1);
        jb_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });

        p1=new JPanel();
        p1.add(p1_lab1);
        p1.add(p1_jtf1);
        p1.add(jb_query);
        p1.add(jb_refresh);
        /**
         * 中间表格
         */
        jtable=new JTable();
        mm=new MenuModel();
        String [] params={"1"};
        mm.query("select * from 菜单 where 1=?",params);
        jtable=new JTable(mm);
        jtable.setSize(500,500);
        jsp=new JScrollPane(jtable);
        p2=new JPanel();
        p2.add(jsp);

        /**
         * 添加
         */
        jb_add=new JButton("添加");
        jb_add.setFont(MyTools.f3);
        jb_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要添加菜品");
                new AddMenu(window1,"添加新菜品",true);
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        /**
         * 修改
         */
        jb_modify=new JButton("修改");
        jb_modify.setFont(MyTools.f3);
        jb_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要修改菜品信息");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(window1,"请选择要修改的菜品","提示",JOptionPane.INFORMATION_MESSAGE);
                    return ;
                }
                new ModifyMenu(window1,"修改菜品信息",true,mm,selectedRow);//这里传入的empNo就是员工号(int)
                mm=new MenuModel();
                String [] params={"1"};
                //关闭详细信息界面后显示的信息，要与点击之前的保持一致
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
                jsp=new JScrollPane(jtable);
                p2.add(jsp);
            }
        });
        /**
         * 删除
         */
        jb_delete=new JButton("删除");
        jb_delete.setFont(MyTools.f3);
        jb_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户要删除");
                int selectedRow = jtable.getSelectedRow();//返回选择的行数
                String empNo=(String)mm.getValueAt(selectedRow,0);
                System.out.println("员工编号："+empNo);
                if(mm.delMenuById(empNo)){
                    JOptionPane.showMessageDialog(null,"删除成功");
                }else{
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
                String []params={"1"};
                mm=new MenuModel();//如果不重新new一个数据模型，则更新的是之前定义的EmpModel
                //更新数据模型
                mm.query("select * from 菜单 where 1=?",params);
                jtable.setModel(mm);
            }
        });

        p3=new JPanel();
        p3.add(jb_add);
        p3.add(jb_modify);
        p3.add(jb_delete);

        this.setLayout(new BorderLayout());
        this.add(p1,"North");
        this.add(p2,"Center");
        this.add(p3,"South");

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
